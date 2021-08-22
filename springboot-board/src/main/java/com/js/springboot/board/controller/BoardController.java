package com.js.springboot.board.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.js.springboot.board.model.service.BoardService;
import com.js.springboot.board.model.vo.Attachment;
import com.js.springboot.board.model.vo.Board;
import com.js.springboot.board.model.vo.BoardExt;
import com.js.springboot.common.SpringUtils;

import lombok.extern.slf4j.Slf4j;

//@RestController
@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ServletContext application; //생명주기가 제일 긴 객체(서버시작~서버끝)

	@Autowired
	private ResourceLoader resourceLoader;
	
	/**
	 * 전체 게시글 조회
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/boardList.do")
	public String boardList(@RequestParam(required = true, defaultValue = "1") int cpage, Model model, HttpServletRequest request) {
		try {
			//log.debug("cpage = {}",cpage);
			final int limit = 10;
			final int offset = (cpage - 1) * limit;
			
			Map<String,Object> param = new HashMap<>();
			param.put("limit", limit);
			param.put("offset", offset);
			//전체 게시글 리스트 조회
			List<Board> list = boardService.selectBoardList(param);
			//log.debug("list = {}", list); //list = [BoardExt(super=Board(no=3, title=금요일이다, content=오늘은 금요일입니다.확인부탁드립니다., regDate=Fri Jul 02 00:00:00 KST 2021, readCount=0), hasAttachment=false, attachList=null),BoardExt()....] 
			//전체 게시글수 조회
			int totalContents = boardService.selectBoardCount(); 
			//log.debug("totalContents = {}",totalContents);
			
			//요청 URI
			String url = request.getRequestURI();
			//log.debug("url = {}", url); //url = /springboot-board/board/boardList.do
			
			//페이징 - 유틸 클래스파일 이용
			String pageBar = SpringUtils.getPageBar(cpage,limit,totalContents,url);
			//log.debug("pageBar = {}", pageBar);
			
			
			
			model.addAttribute("list", list);
			model.addAttribute("pageBar", pageBar);
		} catch(Exception e) {
			log.error("게시글 조회 오류!", e);
			throw e;
		}
		
		return "/views/board/boardList.jsp";
	}
	
	/**
	 * 게시글 등록(GET)
	 * @return
	 */
	@GetMapping("/boardForm.do")
	public String boardForm() {
		return "/views/board/boardForm.jsp";
	}
	
	/**
	 * 게시글 등록(POST)
	 * @param board
	 * @param upFiles
	 * @param redirectAttr
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/boardEnroll.do")
	public String boardEnroll(@ModelAttribute BoardExt board, @RequestParam(name = "upFile") MultipartFile[] upFiles, RedirectAttributes redirectAttr, Model model) throws Exception {
		//BoardExt board2 = null;
		try {
			//log.debug("board = {}", board);
			
			//1. 파일저장 : 절대경로/resources/upload/board
			//pageContext:PageContext - request:HttpServletRequest - session:HttpSession - application:ServletContext
			String saveDirectory = application.getRealPath("/resources/upload/board");
			//log.debug("saveDirectory = {}", saveDirectory);
			
			//2. 업무로직 : db저장 board, attachment
			File dir = new File(saveDirectory);
			if(!dir.exists())
				dir.mkdirs(); //dir이 존재하지않다면 복수개의 디렉토리를 생성
			
			List<Attachment> attachList = new ArrayList<>();
			
			for(MultipartFile upFile : upFiles) {
				//input[name=upFile]로부터 비어있는 upFile이 넘어온다.
				if(upFile.isEmpty())
					continue;
				
				String renamedFilename = SpringUtils.getRenamedFilename(upFile.getOriginalFilename());
				//a. 서버컴퓨터에 저장
				File dest = new File(saveDirectory, renamedFilename);
				upFile.transferTo(dest); //전송(파일이동)
				
				//b. 저장된 데이터를 Attachment객체에 저장 및 list에 추가
				Attachment attach = new Attachment();
				attach.setOriginalFileName(upFile.getOriginalFilename());
				attach.setRenamedFileName(renamedFilename);
				attachList.add(attach);
			}
			//log.debug("attachList = {}",attachList);
			//board객체에 설정
			board.setAttachList(attachList);
			//db저장, attachment
			int result = boardService.insertBoard(board);
			
			//게시글 등록후 다시 상세페이지로 이동하기위해 조회
			BoardExt board2 = boardService.selectOneBoard(board.getNo());
			//log.debug("board2 = {}", board2);
			
			//3. 사용자피드백 & 리다이렉트
			//log.debug("attachList = {}", attachList);
			
			redirectAttr.addFlashAttribute("msg", "게시글 등록 성공!");
			model.addAttribute("board", board2);
			
		} catch(Exception e) {
			log.error("게시글 등록 오류!",e);
			throw e;
		}
		
		return "redirect:/board/boardDetail.do?no=" + board.getNo();
		
	}
	
	/**
	 * 게시글 상세 조회
	 * @param no
	 * @param model
	 * @return
	 */
	@GetMapping("/boardDetail.do")
	public String selectOneBoard(@RequestParam int no, Model model) {
		BoardExt board = boardService.selectOneBoard(no);
		//BoardExt board = boardService.selectOneBoardCollection(no);
		//log.debug("board = {}", board);
		
		model.addAttribute("board", board);
		//return "redirect:/board/boardDetail.do?no=" + board.getNo();
		return "/views/board/boardDetail.jsp";
	}
	
	/**
	 * 게시글 수정(GET)
	 * @param no
	 * @param model
	 * @return
	 */
	@GetMapping("/boardUpdate.do")
	public String boardUpdate(@RequestParam int no, Model model,@RequestHeader(name = "Referer", required = false) String referer) {
		BoardExt board = boardService.selectOneBoard(no);
		//log.debug("board = {}", board);
		//log.debug("referer = {}",referer);
		if(referer != null)
			model.addAttribute("referer", referer);
		model.addAttribute("board", board);
		
		return "/views/board/boardUpdate.jsp";
	}
	
	/**
	 * 게시글 수정(POST)
	 * @param board
	 * @param upFiles
	 * @param hasAttachment
	 * @param delAttachmentYN
	 * @param redirectAttr
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(@ModelAttribute BoardExt board, 
							  @RequestParam(name = "upFile", required = false) MultipartFile[] upFiles, 
							  @RequestParam(name = "hasAttachment", required = false) boolean hasAttachment,
							  @RequestParam(name = "delAttachmentYN", required = false) boolean delAttachmentYN,
							  RedirectAttributes redirectAttr)
	throws Exception {
				
		try {
			//log.debug("board = {}", board);
			//log.debug("hasAttachment = {}", hasAttachment); //첨부파일 여부
			//log.debug("delAttachmentYN = {}", delAttachmentYN); //첨부파일 삭제여부			
			String saveDirectory = application.getRealPath("/resources/upload/board");
			//log.debug("saveDirectory = {}", saveDirectory);
			
			File dir = new File(saveDirectory);
			if(!dir.exists())
				dir.mkdirs();
			
			List<Attachment> attachList = new ArrayList<>();
			
			//첨부파일이 없는 게시글을 수정하였을 경우
			if(!hasAttachment) {
				for(MultipartFile upFile : upFiles) {
					if(upFile.isEmpty())
						continue;
					
					String renamedFilename = SpringUtils.getRenamedFilename(upFile.getOriginalFilename());
					File dest = new File(saveDirectory, renamedFilename);
					upFile.transferTo(dest); 
					
					Attachment attach = new Attachment();
					attach.setOriginalFileName(upFile.getOriginalFilename());
					attach.setRenamedFileName(renamedFilename);
					attachList.add(attach);
				}
				delAttachmentYN = false; //첨부파일 변경인 경우
				//int attachNo = boardService.selectAttachmentNo(board.getNo()); //상태를 변경할 첨부파일 번호 조회
				//int updateAttachmentN = boardService.updatePrevAttachment(attachNo); //첨부파일 상태 변경(Y -> N)
				int deleteAttachment = boardService.deleteAttachment(board.getNo()); //이전 첨부파일 삭제
				//log.debug("attachList = {}",attachList);
				board.setAttachList(attachList);				
				//log.debug("board3 = {}", board);
			}
			int result = boardService.updateBoard(board,delAttachmentYN);
			
			BoardExt board2 = boardService.selectOneBoard(board.getNo());
			//log.debug("board2 = {}", board2);
			
			redirectAttr.addFlashAttribute("msg", "게시글 수정 성공!");
			//model.addAttribute("board", board2);
			
		} catch(Exception e) {
			log.error("게시글 수정 오류!",e);
			throw e;
		}
		
		return "redirect:/board/boardDetail.do?no=" + board.getNo();
	}
	
	/**
	 * 게시글 삭제
	 * @param no
	 * @param redirectAttr
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/boardDelete.do")
	public String boardDelete(@RequestParam int no, RedirectAttributes redirectAttr) throws Exception {
		try {
			//log.debug("boardNo = {}", no);
			int result = boardService.deleteBoard(no);
			redirectAttr.addFlashAttribute("msg", "게시글 삭제 성공!");
			
		}catch(Exception e) {
			log.error("게시글 삭제 오류!",e);
			throw e;
		}
		
		return "redirect:/board/boardList.do";
	}
	
	
	/**
	 * 첨부파일 다운로드
	 * ResponseEntity
	 * 1. status code 커스터마이징
	 * 2. 응답 header 커스터마이징
	 * 3. @ResponseBody 기능 포함
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@GetMapping("/fileDownload.do")
	public ResponseEntity<Resource> fileDownloadWithResponseEntity(@RequestParam int no) throws UnsupportedEncodingException{
		ResponseEntity<Resource> responseEntity = null;
		
		try {
		
			//1. 업무로직
			Attachment attach = boardService.selectOneAttachment(no);
			if(attach == null) {
				return ResponseEntity.notFound().build(); //Status code 커스터마이징이 가능하다(404)
			}
			
			//2. Resource객체
			String saveDirectory = application.getRealPath("/resources/upload/board");
			File downFile = new File(saveDirectory, attach.getRenamedFileName());
			Resource resource = resourceLoader.getResource("file:" + downFile);
			String filename = new String(attach.getOriginalFileName().getBytes("utf-8"),"iso-8859-1");
			
			//3. ResponseEntity객체 생성
			//builder패턴
			//응답 header 커스터마이징
			responseEntity = ResponseEntity
											.ok()
											.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE) //Content-Type:application/octet-stream;charset=utf-8
											.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename)
											.body(resource);
			
		} catch(Exception e) {
			log.error("파일다운로드 오류",e);
			throw e;
		}
		return responseEntity;
	}
	
	/**
	 * 게시글 검색(제목) - 자동완성
	 * @param searchTitle
	 * @return
	 */
	@GetMapping("/boardSearch.do")
	@ResponseBody
	public Map<String, Object> boardSrchList(@RequestParam String searchTitle) {
		//log.debug("searchTitle = {}", searchTitle); //검색어
				
		List<Board> list = boardService.searchTitle(searchTitle);
		//log.debug("list = {}", list);
				
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("searchTitle", searchTitle);
		
		return map;
	}
	
	
}

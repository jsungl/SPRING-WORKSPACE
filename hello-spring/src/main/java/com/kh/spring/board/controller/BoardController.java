package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.BoardExt;
import com.kh.spring.common.util.HelloSpringUtils;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
	
	@Autowired
	private ServletContext application; //생명주기가 제일 긴 객체(서버시작~서버끝)

	@Autowired
	private ResourceLoader resourceLoader;
	
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardList.do")
	public String boardList(@RequestParam(required = true, defaultValue = "1") int cpage, Model model, HttpServletRequest request) {
		try {
			log.debug("cpage = {}",cpage);
			final int limit = 10;
			final int offset = (cpage - 1) * limit;
			
			
			Map<String,Object> param = new HashMap<>();
			param.put("limit", limit);
			param.put("offset", offset);
			
			//1. 업무로직 : content영역 - Rowbounds
			List<Board> list = boardService.selectBoardList(param);
			log.debug("list = {}", list); //List<Board>에는 Board를 상속받은 BoardExt타입의 객체를 리스트형태로 담을수있다.
//			List<Board> anew = new ArrayList<Board>();
//			anew.add(new BoardExt(1,"안녕","홍길동","내이름은",null,0,false));
//			log.debug("anew = {}",anew);
			
			
			int totalContents = boardService.selectBoardCount(); //전체 게시글수
			log.debug("totalContents = {}",totalContents);
			String url = request.getRequestURI(); // /spring/board/boardList.do
			log.debug("url = {}", url);
			String pageBar = HelloSpringUtils.getPageBar(cpage,limit,totalContents,url);
			log.debug("pageBar = {}", pageBar);
			
			
			//2. jsp에 위임
		
			model.addAttribute("list", list);
			model.addAttribute("pageBar", pageBar);
		
		} catch(Exception e) {
			log.error("게시글 조회 오류!", e);
			throw e;
		}
		
		return "board/boardList";
	}
	
	@GetMapping("/boardForm.do")
	public void boardForm() {
		
	}
	
	@PostMapping("/boardEnroll.do")
	public String boardEnroll(@ModelAttribute BoardExt board, @RequestParam(name = "upFile") MultipartFile[] upFiles, RedirectAttributes redirectAttr) throws Exception {
		try {
			log.debug("board = {}", board);
			for(MultipartFile upFile : upFiles) {
				log.debug("upFile = {}", upFile);
				log.debug("upFile.name = {}", upFile.getOriginalFilename());
				log.debug("upFile.size = {}", upFile.getSize());
				log.debug("-----------------------------------------------");
				
			}
			
			//1. 파일저장 : 절대경로/resources/upload/board
			//pageContext:PageContext - request:HttpServletRequest - session:HttpSession - application:ServletContext
			String saveDirectory = application.getRealPath("/resources/upload/board");
			log.debug("saveDirectory = {}", saveDirectory);
			
			//2. 업무로직 : db저장 board, attachment
			File dir = new File(saveDirectory);
			if(!dir.exists())
				dir.mkdirs(); //dir이 존재하지않다면 복수개의 디렉토리를 생성
			
			List<Attachment> attachList = new ArrayList<>();
			
			for(MultipartFile upFile : upFiles) {
				//input[name=upFile]로부터 비어있는 upFile이 넘어온다.
				if(upFile.isEmpty())
					continue;
				
				String renamedFilename = HelloSpringUtils.getRenamedFilename(upFile.getOriginalFilename());
				//a. 서버컴퓨터에 저장
				File dest = new File(saveDirectory, renamedFilename);
				upFile.transferTo(dest); //전송(파일이동)
				
				//b. 저장된 데이터를 Attachment객체에 저장 및 list에 추가
				Attachment attach = new Attachment();
				attach.setOriginalFileName(upFile.getOriginalFilename());
				attach.setRenamedFileName(renamedFilename);
				attachList.add(attach);
			}
			log.debug("attachList = {}",attachList);
			//board객체에 설정
			board.setAttachList(attachList);
			//db저장, attachment
			int result = boardService.insertBoard(board);
			
			//3. 사용자피드백 & 리다이렉트
			log.debug("attachList = {}", attachList);
			redirectAttr.addFlashAttribute("msg", "게시글 등록 성공!");
			
		} catch(Exception e) {
			log.error("게시글 등록 오류!",e);
			throw e;
		}
		
		return "redirect:/board/boardDetail.do?no=" + board.getNo();
	}
	
	
	@GetMapping("/boardDetail.do")
	public void selectOneBoard(@RequestParam int no, Model model) {
		//1. 업무로직 : board - attachment
		BoardExt board = boardService.selectOneBoard(no);
		//BoardExt board = boardService.selectOneBoardCollection(no);
		log.debug("board = {}", board);
		
		//2. jsp에 위임
		model.addAttribute("board", board);
		
	}
	
	/**
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
	
	
	
	
//	@GetMapping(value = "/fileDownload.do", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE) //Content-Type : image/jpeg;charset=utf-8
	@ResponseBody //응답메세지에 return객체를 직접 출력
	public Resource fileDownload(@RequestParam int no, HttpServletResponse response) throws UnsupportedEncodingException {
		
		//1. 업무로직 : db에서 첨부파일 정보 조회
		Attachment attach = boardService.selectOneAttachment(no);
		log.debug("attach = {}", attach);
		if(attach == null) {
			throw new IllegalArgumentException("해당 첨부파일은 존재하지 않습니다 : " + no);
		}
		
		//2. Resource객체를 리턴 : 응답메세지에서 출력은 spring-container가 처리
		String originalFilename = attach.getOriginalFileName();
		String renamedFilename = attach.getRenamedFileName();
		String saveDirectory = application.getRealPath("/resources/upload/board");
		File downFile = new File(saveDirectory,renamedFilename);
		
		//웹상자원, 서버컴퓨터자원을 모두 다룰수있는 스프링의 추상화 layer
		String location = "file:" + downFile.toString();
		//String location = "https://docs.oracle.com/javase/8/docs/api/java/lang/String.html";
		
		log.debug("location = {}", location);
		Resource resource = resourceLoader.getResource(location);
		
		
		//응답헤더
		//한글깨짐방지처리
		//response.setContentType("application")
		originalFilename = new String(originalFilename.getBytes("utf-8"), "iso-8859-1");
		//originalFilename = "String.html";
		
		//response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE); //위 GetMapping에 적는게 우선순위가 더 높다
		response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + originalFilename);
		
		return resource;
		
	}
	
	
	@GetMapping("/boardSearch.do")
	@ResponseBody
	public Map<String, Object> boardSrchList(@RequestParam String searchTitle) {
		log.debug("searchTitle = {}", searchTitle); //검색어
				
		//1. 업무로직 : 검색어로 board조회
		List<Board> list = boardService.searchTitle(searchTitle);
		log.debug("list = {}", list);
				
		//2. map에 검색결과를 담아서 전송
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("searchTitle", searchTitle);
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

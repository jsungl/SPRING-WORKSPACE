package com.js.springboot.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.js.springboot.board.model.dao.BoardDao;
import com.js.springboot.board.model.vo.Attachment;
import com.js.springboot.board.model.vo.Board;
import com.js.springboot.board.model.vo.BoardExt;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	/**
	 * 게시글 전체 리스트 조회
	 */
	@Override
	public List<Board> selectBoardList(Map<String, Object> param) {
		return boardDao.selectBoardList(param);
	}
	
	/**
	 * 게시글 1개 조회
	 */
	@Override
	public BoardExt selectOneBoard(int no) {
		int result = boardDao.updateReadCount(no);
		BoardExt board = boardDao.selectOneBoard(no);
		List<Attachment> attachList = boardDao.selectAttachList(no); //pk조회가 아닌 boardNo로 조회
		//log.debug("attachList = {}", attachList);
		board.setAttachList(attachList);
		return board;
	}
	
	/**
	 * 게시글 등록
	 */
	@Override
	public int insertBoard(BoardExt board) {
		int result = 0;
		//1. board 등록
		result = boardDao.insertBoard(board);
		//log.debug("board = {}", board); //board객체의 no값이 저장되어 나온다(mapper로 넘길때는 0이였음)
		
		//2. attachment 등록(첨부파일이 있는경우)
		if(board.getAttachList().size() > 0) {
			for(Attachment attach : board.getAttachList()) {
				//attach.setBoardNo("이번에발급받은 board pk");
				attach.setBoardNo(board.getNo()); // board no fk 세팅
				//insertAttachment() 호출
				result = insertAttachment(attach);
			}
		}
		
		return result;
	}
	
	/**
	 * 첨부파일 등록
	 * @param attach
	 * @return
	 */
	public int insertAttachment(Attachment attach) {
		return boardDao.insertAttachment(attach);
	}
	
	/**
	 * 첨부파일 삭제
	 */
	@Override
	public int deleteAttachment(int no) {
		return boardDao.deleteAttachment(no);
	}
	
	
	/**
	 * 게시글 수정
	 */
	@Override
	public int updateBoard(BoardExt board, boolean delAttachmentYN) {
		int result = 0;
		result = boardDao.updateBoard(board);
		//log.debug("board = {}", board);
		//log.debug("delAttachmentYN = {}", delAttachmentYN);
		
		//2. attachment 등록(첨부파일이 있는경우)
		if(board.getAttachList() != null && board.getAttachList().size() > 0) {
			if(!delAttachmentYN) {				
				for(Attachment attach : board.getAttachList()) {
					//attach.setBoardNo("이번에발급받은 board pk");
					attach.setBoardNo(board.getNo()); // board no fk 세팅
					//insertAttachment() 호출
					result = insertAttachment(attach);
				}
			}
		}
		
		if(delAttachmentYN)
			result = deleteAttachment(board.getNo());
		
		return result;
	}
	
	/**
	 * 게시글 삭제
	 */
	@Override
	public int deleteBoard(int no) {
		return boardDao.deleteBoard(no);
	}
	
	/**
	 * 첨부파일 조회
	 */
	@Override
	public Attachment selectOneAttachment(int no) {
		return boardDao.selectOneAttachment(no);
	}
	
	
	/**
	 * 첨부파일 번호 조회
	 */
	@Override
	public int selectAttachmentNo(int no) {
		return boardDao.selectAttachmentNo(no);
	}
		
	/**
	 * 전체 게시글 수 조회
	 */
	@Override
	public int selectBoardCount() {
		return boardDao.selectBoardCount();
	}

	/**
	 * 게시글 검색(제목검색)
	 */
	@Override
	public List<Board> searchTitle(String searchTitle) {
		return boardDao.searchTitle(searchTitle);
	}


}

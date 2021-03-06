package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.BoardExt;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	
	@Autowired
	private BoardDao boardDao;

	@Override
	public List<Board> selectBoardList() {
		return boardDao.selectBoardList();
	}

	@Override
	public List<Board> selectBoardList(Map<String, Object> param) {
		return boardDao.selectBoardList(param);
	}

	//전체 게시글 수
	@Override
	public int selectBoardCount() {
		return boardDao.selectBoardCount();
	}

	/**
	 * rollbackFor - 트랜잭션 rollback처리하기위한 예외 등록. Exception -> 모든예외
	 * 				 기본적으로는 RuntimeException만 rollback한다.
	 */
	//@Transactional(rollbackFor = Exception.class) //transaction관리
	@Override
	public int insertBoard(BoardExt board) {
		int result = 0;
		//1. board 등록
		result = boardDao.insertBoard(board);
		log.debug("board = {}", board); //board객체의 no값이 저장되어 나온다(mapper로 넘길때는 0이였음)
		
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
	
	//@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertAttachment(Attachment attach) {
		return boardDao.insertAttachment(attach);
	}

	@Override
	public BoardExt selectOneBoard(int no) {
		BoardExt board = boardDao.selectOneBoard(no);
		List<Attachment> attachList = boardDao.selectAttachList(no); //pk조회가 아닌 boardNo로 조회
		board.setAttachList(attachList);
		return board;
	}

	@Override
	public BoardExt selectOneBoardCollection(int no) {
		return boardDao.selectOneBoardCollection(no);
	}

	@Override
	public Attachment selectOneAttachment(int no) {
		return boardDao.selectOneAttachment(no);
	}

	@Override
	public List<Board> searchTitle(String searchTitle) {
		return boardDao.searchTitle(searchTitle);
	}
}

package com.js.springboot.board.model.service;

import java.util.List;
import java.util.Map;

import com.js.springboot.board.model.vo.Attachment;
import com.js.springboot.board.model.vo.Board;
import com.js.springboot.board.model.vo.BoardExt;

public interface BoardService {

	List<Board> selectBoardList(Map<String, Object> param);

	BoardExt selectOneBoard(int no);

	int insertBoard(BoardExt board);
	
	//int insertAttachment(Attachment attach);
	
	int deleteAttachment(int no);

	int updateBoard(BoardExt board, boolean delAttachmentYN);

	int deleteBoard(int no);

	Attachment selectOneAttachment(int no);

	int selectAttachmentNo(int no);

	//int updatePrevAttachment(int attachNo);

	int selectBoardCount();

	List<Board> searchTitle(String searchTitle);

}

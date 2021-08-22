package com.js.springboot.board.model.dao;

import java.util.List;
import java.util.Map;

import com.js.springboot.board.model.vo.Attachment;
import com.js.springboot.board.model.vo.Board;
import com.js.springboot.board.model.vo.BoardExt;

public interface BoardDao {

	List<Board> selectBoardList(Map<String, Object> param);

	int updateReadCount(int no);

	BoardExt selectOneBoard(int no);

	int insertBoard(BoardExt board);

	int insertAttachment(Attachment attach);

	List<Attachment> selectAttachList(int no);

	int updateBoard(BoardExt board);

	int deleteBoard(int no);

	Attachment selectOneAttachment(int no);

	int deleteAttachment(int no);

	int selectAttachmentNo(int no);

	int updatePrevAttachment(int attachNo);

	int selectBoardCount();

	List<Board> searchTitle(String searchTitle);


}

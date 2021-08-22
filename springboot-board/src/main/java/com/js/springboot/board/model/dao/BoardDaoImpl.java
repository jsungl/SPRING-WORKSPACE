package com.js.springboot.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.js.springboot.board.model.vo.Attachment;
import com.js.springboot.board.model.vo.Board;
import com.js.springboot.board.model.vo.BoardExt;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<Board> selectBoardList(Map<String, Object> param) {
		int offset = (int)param.get("offset");
		int limit = (int)param.get("limit");
		RowBounds rowBounds = new RowBounds(offset,limit);
		return session.selectList("board.selectBoardList", null, rowBounds);
	}
	
	@Override
	public int updateReadCount(int no) {
		return session.update("board.updateReadCount", no);
	}

	@Override
	public BoardExt selectOneBoard(int no) {
		return session.selectOne("board.selectOneBoard",no);
	}

	@Override
	public int insertBoard(BoardExt board) {
		return session.insert("board.insertBoard", board);
	}

	@Override
	public int insertAttachment(Attachment attach) {
		return session.insert("board.insertAttachment", attach);
	}

	@Override
	public List<Attachment> selectAttachList(int boardNo) {
		return session.selectList("board.selectAttachList",boardNo);
	}

	@Override
	public int updateBoard(BoardExt board) {
		return session.update("board.updateBoard", board);
	}

	@Override
	public int deleteBoard(int no) {
		return session.delete("board.deleteBoard", no);
	}

	@Override
	public Attachment selectOneAttachment(int no) {
		return session.selectOne("board.selectOneAttachment", no);
	}

	@Override
	public int deleteAttachment(int no) {
		return session.update("board.deleteAttachment", no);
	}

	@Override
	public int selectAttachmentNo(int no) {
		return session.selectOne("board.selectAttachmentNo",no);
	}

	@Override
	public int updatePrevAttachment(int attachNo) {
		return session.update("board.updatePrevAttachment",attachNo);
	}

	@Override
	public int selectBoardCount() {
		return session.selectOne("board.selectBoardCount");
	}

	@Override
	public List<Board> searchTitle(String searchTitle) {
		return session.selectList("board.searchTitle",searchTitle);
	}

	
}

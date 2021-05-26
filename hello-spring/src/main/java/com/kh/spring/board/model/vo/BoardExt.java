package com.kh.spring.board.model.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BoardExt extends Board {

	//private int attachCount;
	

//	public BoardExt(int no, String title, String memberId, String content, Date regDate, int readCount,
//			int attachCount) {
//		super(no, title, memberId, content, regDate, readCount);
//		this.attachCount = attachCount;
//	}
	
	
	private boolean hasAttachment;
	private List<Attachment> attachList;

//	public BoardExt(int no, String title, String memberId, String content, Date regDate, int readCount, boolean hasAttachment) {
//		super(no, title, memberId, content, regDate, readCount);
//		this.hasAttachment = hasAttachment;
//	}
	
	
	
	
}

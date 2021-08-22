package com.js.springboot.board.model.vo;

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

	private boolean hasAttachment;
	private List<Attachment> attachList;
}

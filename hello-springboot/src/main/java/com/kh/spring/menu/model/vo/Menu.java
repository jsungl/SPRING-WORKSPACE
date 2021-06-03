package com.kh.spring.menu.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {

	private int id;
	private String restaurant;
	private String name;
	private int price;
	//상수처리 -> enum
	private MenuType type; //kr, ch, jp
	private String taste; //mild, hot
}

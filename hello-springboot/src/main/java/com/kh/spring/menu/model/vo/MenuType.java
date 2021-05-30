package com.kh.spring.menu.model.vo;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 암묵적으로 java.lang.Enum을 상속
 * 위에서 선언된 valueOf와 충돌 -> 따로 선언
 * @author suver21
 *
 */
public enum MenuType {

	KR("kr"), CH("ch"), JP("jp");
	
	private String value;
	
	/**
	 * enum 생성자의 접근제한자는 private
	 * 외부에서 접근/생성 불가(new)
	 * @param value
	 */
	MenuType(String value){
		this.value = value;
	}
	
	@JsonValue //KR -> kr, CH -> ch, JP -> jp
	public String getValue() {
		return this.value;
	}
	
	public static MenuType menuTypeValueOf(String value) {
		switch(value) {
		case "kr" : return KR;
		case "jp" : return JP;
		case "ch" : return CH;
		default:
			throw new AssertionError("Unknown MenuType : " + value);
		
		}
	}
}

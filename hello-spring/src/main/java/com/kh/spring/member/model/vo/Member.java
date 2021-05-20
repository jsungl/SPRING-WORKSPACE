package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;

//import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsContructor
//@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	//@NotNull
	private String id;
	
	private String password;
	private String name;
	private String gender;
	private Date birthday; //시분초 정보가 필요하면 util.Date 사용
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrollDate;
	private boolean enabled; //회원활성화여부(정상상태,휴면상태) : true(1), false(0)
	
	
	
	
}

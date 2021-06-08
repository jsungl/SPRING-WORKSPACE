package com.kh.spring.member.model.vo;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member implements UserDetails{
//UserDetails를 구현해야 security에서 권한검사를 할수있다
	private String id;	//username
	private String password;	//password
	private String name;
	private String gender;
	private Date birthday;
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrollDate;
	private boolean enabled;	//활성화여부
	
	//복수개의 권한을 관리
	//SimpleGrantedAuthority : 문자열 data("ROLE_USER","ROLE_ADMIN")를 처리할수있는 GrantedAuthority의 하위 클래스
	private List<SimpleGrantedAuthority> authorities; //authorities 
	
	
	/**
	 * Collection - List/Set
	 * 
	 * Collection<? extends GrantedAuthority> : GrantedAuthority를 상속하는 ? -> 자식타입(상한선)
	 * ex) <? super Integer> -> Integer 부모타입(하한선)
	 * 
	 * 
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getUsername() {
		return id;
	}
	//유효기간 만료확인 & 잠겨있는지 확인
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	
}

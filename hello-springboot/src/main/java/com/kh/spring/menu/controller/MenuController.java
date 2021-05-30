package com.kh.spring.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.menu.model.service.MenuService;
import com.kh.spring.menu.model.vo.Menu;

import lombok.extern.slf4j.Slf4j;


/**
 * @RestController = @Controller + @ResponseBody
 * 모든 handler는 @ResponseBody로 처리된다. 리턴값이 json으로 변환후 처리된다
 * 
 */
@RestController
@Slf4j
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	
	@GetMapping("/menus")
	public List<Menu> menus(){
		//1. 업무로직
		List<Menu> list = menuService.selectMenuList();
		log.debug("list = {}", list); //list = [Menu(id=19, restaurant=진씨화로, name=돌솥비빔밥, price=7000, type=KR, taste=mild), .....]
		//2. 리턴하면 @ResponseBody에 의해서 json변환후 응답출력 처리
		
		return list;
	}
}

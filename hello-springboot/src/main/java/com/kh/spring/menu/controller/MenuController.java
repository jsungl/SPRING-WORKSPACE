package com.kh.spring.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		try {
			//1. 업무로직
			List<Menu> list = menuService.selectMenuList();
			log.debug("list = {}", list); //list = [Menu(id=19, restaurant=진씨화로, name=돌솥비빔밥, price=7000, type=KR, taste=mild), .....]
			//2. 리턴하면 @ResponseBody에 의해서 json변환후 응답출력 처리
			return list;			
		} catch (Exception e) {
			log.error("/menus 오류",e);
			throw e;
		}
	}
	
	/**
	 * @PathVariable 경로변수
	 * @return
	 */
	@GetMapping("/menus/{type}/{taste}") //{type} : 변수로사용
	public List<Menu> menuByType(@PathVariable String type, @PathVariable String taste){
		try {
			log.debug("type = {}, taste = {}", type,taste); //type = kr, taste = hot
			Map<String,Object> param = new HashMap<>();
			param.put("type", type);
			param.put("taste", taste);
			
			List<Menu> list = menuService.selectMenuListByTypeAndTaste(param);
			log.debug("list = {}",list);
			
			return list; //이후는 @responseBody가 처리해줌
		} catch (Exception e) {
			log.error("/menus/type 오류",e);
			throw e;
		}
	}
	
	
	/**
	 * @RequestBody 요청메세지의 body에 작성된 json문자열을 java객체로 변환 
	 * @param menu
	 * @return
	 */
	@PostMapping("/menu")
	public Map<String,Object> menuEnroll(@RequestBody Menu menu) {
		try {
			log.debug("menu = {}", menu); //Menu(id=0, restaurant=봉구스, name=제육, price=3500, type=KR, taste=mild)
			
			int result = menuService.insertMenu(menu);		
			Map<String,Object> map = new HashMap<>();
			map.put("msg", "메뉴등록성공!");
			return map;
		} catch (Exception e) {
			log.error("메뉴등록 실패",e);
			throw e;
		}
	}
	
	
	@GetMapping("/menu/{id}")
	public Map<String,Object> menuById(@PathVariable String id, Model model){
		try {
			log.debug("id = {}",id);
			Map<String,Object> map = new HashMap<>();
			
			Menu menu = menuService.selectMenuById(id);
			log.debug("menu = {}",menu); //Menu(id=21, restaurant=봉구스, name=제육밥버거, price=3500, type=KR, taste=mild)
			if(menu == null)
				map.put("msg", "해당 메뉴가 없습니다.");	
			else
				map.put("msg", null);
			
			map.put("menu", menu);
			return map;
		} catch (Exception e) {
			log.error("메뉴조회(번호) 실패",e);
			throw e;
		}
	}
	
	
	@PutMapping("/menu")
	public Map<String,Object> menuUpdate(@RequestBody Menu menu){
		try {
			log.debug("menu = {}", menu);
			int result = menuService.updateMenu(menu);
			Map<String,Object> map = new HashMap<>();
			map.put("msg", "메뉴수정 성공!");
			return map;
		} catch (Exception e) {
			log.error("메뉴수정 실패",e);
			throw e;
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

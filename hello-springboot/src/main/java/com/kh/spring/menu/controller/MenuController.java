package com.kh.spring.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	//@CrossOrigin
	@GetMapping("/menus")
	public List<Menu> menus(HttpServletResponse response){
		try {
			//1. 업무로직
			List<Menu> list = menuService.selectMenuList();
			log.debug("list = {}", list); //list = [Menu(id=19, restaurant=진씨화로, name=돌솥비빔밥, price=7000, type=KR, taste=mild), .....]
			//2. 리턴하면 @ResponseBody에 의해서 json변환후 응답출력 처리
			
			//3. 응답헤더에 Access-Controll-Allow-Origin : 특정origin
			response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:9090");
			//response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"); //모든 origin에 연결
			
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
	public Map<String,Object> selectOneMenu(@PathVariable String id, Model model){
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
	
	/**
	 * ResponseEntity를 통해서
	 * 존재하지않는 메뉴번호를 요청한 경우
	 * 404 status code를 응답
	 * @param id
	 * @param model
	 * @return
	 */
//	@GetMapping("/menu/{id}")
//	public ResponseEntity<Menu> selectOneMenu(@PathVariable String id){
//		try {
//			log.debug("id = {}",id);
//			Menu menu = menuService.selectMenuById(id);
//			log.debug("menu = {}",menu); //Menu(id=21, restaurant=봉구스, name=제육밥버거, price=3500, type=KR, taste=mild)
//			if(menu != null) {
//				return ResponseEntity.ok().body(menu);	
//			}else {
//				return ResponseEntity.notFound().build();
//			}
//		} catch (Exception e) {
//			log.error("메뉴조회(번호) 실패",e);
//			throw e;
//		}
//	}
	
//	@DeleteMapping("/menu/{id}")
//	public Map<String, Object> deleteMenu(@PathVariable String id){
//		try {
//			log.debug("id = {}",id);
//			int result = menuService.deleteMenu(id);
//			Map<String, Object> map = new HashMap<>();
//			map.put("msg", "메뉴삭제성공");
//			return map;
//		} catch (Exception e) {
//			log.error("메뉴삭제 실패",e);
//			throw e;
//		}	
//	}
	
	
	
	/**
	 * ResponseEntity
	 * 1. builder패턴
	 * 2. 생성자방식
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/menu/{id}")
	public ResponseEntity<?> deleteMenu(@PathVariable String id){
		try {
			log.debug("id = {}",id);
			int result = menuService.deleteMenu(id);
			if(result > 0) {
				Map<String, Object> map = new HashMap<>();
				map.put("msg", "메뉴삭제성공");
				//return ResponseEntity.ok().body(map);	
				return new ResponseEntity<>(map, HttpStatus.OK);
			}else {
				//return ResponseEntity.notFound().build();
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("메뉴삭제 실패",e);
			throw e;
		}	
	}
	
	
	
	
	
	
	
}

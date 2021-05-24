package com.kh.spring.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.memo.model.service.MemoService;
import com.kh.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {
	
	@Autowired
	private MemoService memoService;

	@GetMapping("/memo.do")
	public ModelAndView selectMemoList(ModelAndView mav,Model model) {
		
		/**
		 * 의존주입 받은 객체는 우리가 작성한 MemoController, MemoServiceImpl, MemoDaoImpl타입 객체가 아닌
		 * proxy객체이다.
		 * 
		 * proxy객체 : controller -> proxy객체 -> service -> proxy객체 -> dao
		 * 1. jdk동적 proxy - interface구현체 class.com.sun.proxy.$Proxy39
		 * 2. cglib - interface구현체 아닌 경우
		 * 
		 */
		log.debug("memoService = {}", memoService.getClass());
		
		
		//1. 업무로직 : memo목록조회
		List<Memo> list = memoService.selectMemoList();
		log.debug("list = {}", list);
		
		//2. jsp에 위임
		mav.addObject("list", list);
		mav.setViewName("memo/memo");
		return mav;
	}
	
	@PostMapping("/insertMemo.do")
	public String insertMemo(Memo memo, RedirectAttributes redirectAttr) {
		log.info("memo = {}", memo);
		
		try {
			int result = memoService.insertMemo(memo);
		
			redirectAttr.addFlashAttribute("msg", "메모 insert성공!");
		
		} catch(Exception e) {
			log.error("메모 insert 오류!",e);
			throw e;
		}
		
		return "redirect:/memo/memo.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

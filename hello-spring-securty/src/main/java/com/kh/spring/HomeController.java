package com.kh.spring;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@GetMapping("/")
	public String home() {
		log.info("home 요청!");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object obj = authentication.getPrincipal();
		log.debug("member = {}", obj);
		return "forward:/index.jsp";
	}
	
	@GetMapping("/board/boardList.do")
	public void boardList() {}

	@GetMapping("/admin/memberList.do")
	public void memberList() {}
	
	@GetMapping("/error/accessDenied.do")
	public void accesDenied() {}
	
}

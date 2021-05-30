package com.kh.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	
	@GetMapping("/")
	//@ResponseBody //리턴값을 응답메시지body에 추가
	public String home() {
		log.debug("/home 요청!");
		//return "hello, springboooooot";
		return "forward:index.jsp";
	}
}

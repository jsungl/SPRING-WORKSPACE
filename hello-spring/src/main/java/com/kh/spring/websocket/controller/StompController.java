package com.kh.spring.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StompController {
	
	/**
	 * 	/app/a로 전달시 /app가 setApplicationDestinationPrefixes에 등록되어있으므로
	 *  @MessageMapping("/a") messageHandler로 전달.
	 *  - prefix 생략하고 작성할것.
	 *  
	 *  @SendTo("/bpp/a")에 의해 SimpleBroker로 전달된다
	 *  - SimpleBroker에 /bpp가 등록되어있어야한다
	 *  
	 *  
	 *  
	 *  
	 * @param msg
	 * @return
	 */
	@MessageMapping("/a")
	@SendTo("/bpp/a")
	public String app(String msg) {
		log.debug("app 요청 : {}", msg);
		return msg;
	}
	
}

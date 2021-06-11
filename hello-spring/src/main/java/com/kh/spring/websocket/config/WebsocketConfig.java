package com.kh.spring.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration //spring 빈
@EnableWebSocket //websocket
public class WebsocketConfig implements WebSocketConfigurer {

	@Autowired
	WebSocketHandler websocketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		//client의 const ws = new WebSocket("ws://localhost:9090/spring/websoocket");에 대응한다
		registry.addHandler(websocketHandler, "/websoocket")
				.withSockJS(); //server side에서도 sockjs선언
	}

	
}

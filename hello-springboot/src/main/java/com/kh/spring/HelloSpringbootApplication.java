package com.kh.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * 
 * 
 * @SpringBootConfiguration
 * 		springboot관련 설정
 * @EnableAutoConfiguration
 * 		application-context를 관리(context는 하나)	
 * @ComponentScan : 
 * 		현재실행클래스가 속한 base-package기준으로 annotation활성화
 * 
 * 위 3개의 어노테이션이 @SpringBootApplication에 포함된다
 */
@SpringBootApplication
public class HelloSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringbootApplication.class, args); //내장서버를 실행시킨다
	}

}

package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component //빈등록
@Aspect //aspect클래스 등록. pointcut, advice를 가지고있음
public class LoggerAspect {
//로깅용
	
//	@Pointcut("execution(* com.kh.spring.memo..selectMemoList(..))")
//	public void loggerPointcut() {
//		
//	}
	
	
	
	/**
	 * Around Advice
	 * 	- JoinPoint 실행전, 실행후에 삽입되어 처리되는 advice(보조업무)
	 * 
	 * 
	 * ProceedingJoinPoint joinPoint : 실제 보조업무 메소드를 가리킨다 ex) controller의 insertMemo, service의 insertMemo, dao의 insertMemo 메소드들
	 */
//	@Around("loggerPointcut()")
//	public Object logger(ProceedingJoinPoint joinPoint) throws Throwable{
//		Signature signature = joinPoint.getSignature();
//		
//		//주업무 joinPoint 실행 전(before)
//		log.debug("------ {} start ------", signature);
//		
//		
//		//주업무 joinPoint 실행
//		Object returnObj = joinPoint.proceed();
//		
//		
//		//주업무 joinPoint 실행 후(after)
//		
//		log.debug("------ {} end ------", signature);
//		
//		return returnObj;
//	}
















}

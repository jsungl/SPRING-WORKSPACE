package com.kh.spring.common.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

/**
 * Advice의 다섯가지타입
 * 
 * 1. Around Advice
 * 2. Before Advice
 * 3. After Advice
 * 4. AfterReturning Advice
 * 5. AfterThrowing Advice
 * 
 *
 */
@Slf4j
//@Component
//@Aspect
public class AdviceSignature {
	//private Logger logger = Logger.getLogger(AdviceSignature.class);
	private StopWatch stopWatch  = new StopWatch();
	
	@Pointcut("execution(* com.kh.aop..*Impl.*(..))")
	public void pointcut() {}
	
	@Before("pointcut()")
	public void beforeAdvice(JoinPoint joinPoint) {
		//타겟메소드 실행전 코드
	}
	
	@Around("pointcut()")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
		Signature signature = joinPoint.getSignature();
		//Target메소드 실행전 코드 
		log.debug("------ {} start ------", signature);
		//stopWatch.start();
		
		//주업무 실행
		Object obj = joinPoint.proceed(); 
		
		//Target메소드 실행후 코드 
		log.debug("------ {} end ------", signature);
		//stopWatch.stop();
		//log.info("insertMemo실행시간 : ", stopWatch.prettyPrint());
		return obj;
	}
	
	@AfterReturning(pointcut="pointcut()", returning="returnObj")
	public void afterReturningAdvice(JoinPoint joinPoint, Object returnObj) {
		//타겟메소드의 리턴 데이터를 다른 용도로 처리할 때 사용함.
		//logger.debug("리턴값  = "+returnObj);
		log.debug("리턴값  = "+returnObj);
	}
	
	@After("pointcut()")
	public void afterAdvice() {
		//타겟메소드의 예외 발생 여부에 상관없이 무조건 수행되는 advice
	}
	
	@AfterThrowing(pointcut="pointcut()", throwing="exceptObj")
	public void afterThrowingAdvice(JoinPoint jp, Exception exceptObj) {
		//타겟메소드 실행중 예외가 발생했을 때, 부가적인 로직을 제공할 목적으로 사용하는 Advice
		String methodName = jp.getSignature().getName();
		//logger.debug(methodName + "() 메소드 수행 중 예외 발생!");
		log.debug(methodName + "() 메소드 수행 중 예외 발생!");
		
		if(exceptObj instanceof IllegalArgumentException) {
			//logger.debug("IllegalArgumentException : 부적합한 값이 입력되었습니다.");
			log.debug("IllegalArgumentException : 부적합한 값이 입력되었습니다.");
		}else if(exceptObj instanceof NumberFormatException) {
			//logger.debug("NumberFormatException : 숫자 형식의 값이 아닙니다.");
			log.debug("NumberFormatException : 숫자 형식의 값이 아닙니다.");
		}else if(exceptObj instanceof Exception) {
			//logger.debug("Exception : "+exceptObj.getMessage());
			log.debug("Exception : "+exceptObj.getMessage());
		}else {
			//logger.debug(exceptObj.getMessage());
			log.debug(exceptObj.getMessage());
		}
	}
}

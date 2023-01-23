package com.dalm;






import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;



@Configuration
@Aspect
@Slf4j(topic = "AOP")
public class DalmAopConfiguration {
	
	


	@Before(value="execution(* com.dalm..*.*(..))  ")
	public void logBefore(JoinPoint joinPoint) {
		String target=joinPoint.getTarget().toString();
		String signature = joinPoint.getSignature().getName();
		
		for(int i=0;i<joinPoint.getArgs().length;i++) {
			log.info("Class {}  Method {} args {} - {}",target,signature,(i+1),objectToJson(joinPoint.getArgs()[i]));
		}
	}
	
	@AfterReturning(value="execution(* com.dalm..*.*(..))",returning = "result")
	public void afterReturn(JoinPoint joinPoint,Object result) {
		String target=joinPoint.getTarget().toString();
		String signature = joinPoint.getSignature().getName();
		log.info("Class {}  Method {} response - {}",target,signature,objectToJson(result));
	
	}
	
	@AfterThrowing(value="execution(* com.dalm..*.*(..))",throwing =   "exception")
	public void afterThrowing(JoinPoint joinPoint,Object exception) {
		String target=joinPoint.getTarget().toString();
		String signature = joinPoint.getSignature().getName();
		log.info("Class {}  Method {} error - {}",target,signature,exception);
	
	}
	@Around(value="execution(* com.dalm..*.*(..))")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String target=joinPoint.getTarget().toString();
		String signature = joinPoint.getSignature().getName();
		Long startTime =System.currentTimeMillis();
		joinPoint.proceed();
		Long endTime =System.currentTimeMillis();
		log.info("Class {}  Method {} time - {}",target,signature,(endTime-startTime));
	
	}
	public String objectToJson(Object obj) {
		ObjectMapper mapper=new ObjectMapper() ;
		try {
			return mapper.writer().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}

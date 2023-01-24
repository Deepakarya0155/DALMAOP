package com.dalm;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Aspect
@Slf4j(topic = "AOP")
public class DalmAopConfiguration {
	
//	@Before(value = "com.dalm.DalmAOPPointCuts.aopall()")
//	public void logBefore(JoinPoint joinPoint) {
//			String target = joinPoint.getTarget().toString();
//			String signature = joinPoint.getSignature().getName();
//
//			for (int i = 0; i < joinPoint.getArgs().length; i++) {
//				log.info("Class {}  Method {} args {} - {}", target, signature, (i + 1),
//						objectToJson(joinPoint.getArgs()[i]));
//			}
//		
//	}
//
//	@AfterReturning(value = "com.dalm.DalmAOPPointCuts.aopall()", returning = "result")
//	public Object afterReturn(JoinPoint joinPoint, Object result) {
//			String target = joinPoint.getTarget().toString();
//			String signature = joinPoint.getSignature().getName();
//			log.info("Class {}  Method {} response - {}", target, signature, objectToJson(result));
//		return result;
//	}
//	
//	
//
//	@AfterThrowing(value = "com.dalm.DalmAOPPointCuts.aopall()", throwing = "exception")
//	public void afterThrowing(JoinPoint joinPoint, Object exception) {
//			String target = joinPoint.getTarget().toString();
//			String signature = joinPoint.getSignature().getName();
//			for (int i = 0; i < joinPoint.getArgs().length; i++) {
//				log.info("Class {}  Method {} args {} - {}", target, signature, (i + 1),
//						objectToJson(joinPoint.getArgs()[i]));
//			}
//			log.info("Class {}  Method {} error - {}", target, signature, exception);
//		
//
//	}

	@Around(value = "com.dalm.DalmAOPPointCuts.aopall()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
			String target = joinPoint.getTarget().toString();
			String signature = joinPoint.getSignature().getName();
			Long startTime = System.currentTimeMillis();
			
			
			try {
				for (int i = 0; i < joinPoint.getArgs().length; i++) {
					log.info("Class {}  Method {} args {} - {}", target, signature, (i + 1),
							objectToJson(joinPoint.getArgs()[i]));
				}
				return joinPoint.proceed();
			} catch (Throwable e) {
				throw e;
			}finally {
				Long endTime = System.currentTimeMillis();
				log.info("Class {}  Method {} time - {}", target, signature, (endTime - startTime));
			}
			
	}

	public String objectToJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writer().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			try {
				return obj.toString();
			}catch(Exception e1) {
				return e1.getMessage();
			}
		}
		
	}

}

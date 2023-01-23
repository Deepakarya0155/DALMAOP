package com.dalm;

import org.aspectj.lang.annotation.Pointcut;

public class DalmAOPPointCuts {

	@Pointcut("@annotation(com.dalm.AopAll)")
	public void aopall() {
		
	}
}

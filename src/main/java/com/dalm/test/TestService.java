package com.dalm.test;

import org.springframework.stereotype.Service;

@Service
public class TestService {

	
	public Long addTwo(Long a, Long b,Long c) {
		if(a<0 || b<0 || c<0) {
			throw new RuntimeException("Negitive number not allowed");
		}
		return a+b+c;
	}
	
}

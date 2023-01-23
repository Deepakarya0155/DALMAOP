package com.dalm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dalm.test.TestService;
import com.dalm.test.test2.Test2Service;

//@SpringBootApplication
public class DalmaopApplication implements CommandLineRunner{

	@Autowired
	TestService testService;
	
	@Autowired
	Test2Service test2Service;
	
	public static void main(String[] args) {
		SpringApplication.run(DalmaopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		Long addTwo = testService.addTwo(10l, 20l,30l);
//		test2Service.addTwo(10l, 20l, 30l);
//		test2Service.addTwo(-10l, 20l, 30l);
	}

}

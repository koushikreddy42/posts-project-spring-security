package com.example.springsecurity.demo;

import com.example.springsecurity.demo.entity.User;
import com.example.springsecurity.demo.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private JwtService jwtService;


	@Test
	void contextLoads() {
		User user = new User(3L, "koushikreddy@gmail.com", "password", "koushik");
		String token = jwtService.generateToken(user);
		System.out.println(token);
		Long userId = jwtService.getUserIdFromToken(token);
		System.out.println(userId);
	}

}

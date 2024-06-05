package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yedam.app.aop.service.AaaService;

@SpringBootTest		//test환경이지만 기존에 설정한걸 그대로 가져오겠다라는 어노테이션
public class TestExample {
	@Autowired
	AaaService aaaService;
	
//	@Test
	public void aaaInsert() {
		aaaService.insert();
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void testEncoder() {
		String password = "1234";
		
		//DB에 저장된 암호화된 비밀번호
		String enPwd = passwordEncoder.encode(password);
		System.out.println("enPwd : " + enPwd);
		
		boolean matchResult = passwordEncoder.matches(password, enPwd);
		System.out.println("match : " + matchResult);
	}
}

package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.aop.service.AaaService;

@SpringBootTest		//test환경이지만 기존에 설정한걸 그대로 가져오겠다라는 어노테이션
public class TestExample {
	@Autowired
	AaaService aaaService;
	
	@Test
	public void aaaInsert() {
		aaaService.insert();
	}
}

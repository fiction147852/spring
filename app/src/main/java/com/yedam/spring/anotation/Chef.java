package com.yedam.spring.anotation;

import org.springframework.stereotype.Component;

@Component	//class 속성만 사용 / id속성은 사용 안함
public class Chef {
	public void cooking() {
		System.out.println("anotation 방식");
	}
}

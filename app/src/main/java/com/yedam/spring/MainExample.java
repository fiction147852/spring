package com.yedam.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainExample {
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");

		TV tv = (TV) ctx.getBean("tv");
		tv.turnOn();

		TV subTv = (TV) ctx.getBean(TV.class);
		subTv.turnOn();

		if (tv == subTv) {
			System.out.println("같은 빈입니다.");
		} else {
			System.out.println("다른 빈입니다.");
		}
	}
}

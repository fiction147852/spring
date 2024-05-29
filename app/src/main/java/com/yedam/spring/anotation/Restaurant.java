package com.yedam.spring.anotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Restaurant {
	
	private Chef chef;
	
	@Autowired	//생성자나 setter나 뭘 하든상관없다 다만 미리 만들어져있고 컨테이너가 활용할곳을 알려주면된다?
	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	public void run() {
		chef.cooking();
	}
}
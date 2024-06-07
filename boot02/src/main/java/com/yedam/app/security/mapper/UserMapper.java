package com.yedam.app.security.mapper;

import com.yedam.app.security.service.UserVO;

public interface UserMapper {	//비밀번호가 맞는지 아닌지는 프로바이더에서하기때문에 그건 고려하지않아도 된다 	//패스워드는 고려안해도된다 이페이지는
	public UserVO getUserInfo(String id);	
}

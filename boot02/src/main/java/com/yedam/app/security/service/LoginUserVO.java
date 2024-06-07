package com.yedam.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter	//@Data는 쓰지말것 로그인정보는 getter를 써야한다 //기본적으로 로그인정보가 유지되도록만 하면되서 setter 상관없다 
public class LoginUserVO implements UserDetails {

	private UserVO userVO;

	public LoginUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	//extends는 상속한다 쉽게 GrantedAuthority이걸 상속받은것만 사용할수있다라고보면된다 아무거나못하게 제한을 거는것
		List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority(userVO.getRoleName()));
		
		return auths;
	}

	@Override
	public String getPassword() {
		return userVO.getPassword();
	}

	@Override
	public String getUsername() {
		return userVO.getLoginId();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 계정 잠금 여부
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 계정 패스워드 만료 여부
		return true;
	}

	@Override
	public boolean isEnabled() { // 계정 사용여부
		return true;
	}

}

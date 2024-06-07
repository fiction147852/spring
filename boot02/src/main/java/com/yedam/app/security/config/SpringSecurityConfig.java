package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	//메모리상 인증정보 등록 => 테스트 전용 방식
//	@Bean
//	InMemoryUserDetailsManager inMemoryUserDetailsService() {
//		UserDetails user = User.builder()
//								.username("user1")
//								.password(passwordEncoder().encode("1234"))
//								.roles("USER")	//ROLE_USER
////								.authorities("ROLE_USER") 권한에 대한 부분인데 편한거쓸것   roles와 authorities 동시에 사용은안함 왜? 헷갈려서
//								.build();
//		
//		
//		UserDetails admin = User.builder()
//								.username("admin1")
//								.password(passwordEncoder().encode("1234"))
//								.roles("ADMIN")	//ROLE_USER
////									.authorities("ROLE_USER") 권한에 대한 부분인데 편한거쓸것   roles와 authorities 동시에 사용은안함 왜? 헷갈려서
//								.build();
//		
//		return new InMemoryUserDetailsManager(user, admin);
//	}
	
	//인증 및 인가 설정
	@Bean
	SecurityFilterChain filterChin(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests() //경로별 권한
			.antMatchers("/", "/all").permitAll()
//			.antMatchers("/user/**").hasRole("USER")	//ROLE_USER	//hasrole(a)은이거아니면 절대안돼 라고하는것 hasanyroll(a,b)은 조금더 추가해준것
			.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")	//ROLE_USER	//hasrole(a)은이거아니면 절대안돼 라고하는것 hasanyroll(a,b)은 조금더 추가해준것
			.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
			.anyRequest().authenticated()	//이걸 정의해주면 다해줘야한다? 		//권한상관없이 나머지 경로에대해서 처리하겠다
		.and()
		.formLogin()
			.defaultSuccessUrl("/all")
		.and()
		.logout()
			.logoutSuccessUrl("/all");
		return http.build();
	}
}

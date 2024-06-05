package com.yedam.app.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect		// AOP의 설정, 어드바이스(횡단관심) + 포인트컷 + 위빙 까지 정보를 저장할것이다
@Component
public class LogAspect {
	//포인트컷 : 조인포인트중에서 Advice(횡단관심)이 적용될 메소드 필터
	@Pointcut("within(com.yedam.app.emp.service.impl.*)")	//
	public void allPointCut() { //이메서드로인해 밑의 포인트컷이 실행된다
		
	}
	
	@Before("allPointCut()")	//allpointcut이 위에 메소드를 불러낸다 
	public void beforAdvice(JoinPoint joinPoint) {
		String signaterStr = joinPoint.getSignature().toString();
		Object[] args = joinPoint.getArgs();
		System.out.println("############# 실행 : " + signaterStr);
		System.out.println("parameter list : " + args.toString());
	}
	
	@Around("allPointCut()")	//실행전후 모두에게 적용
	public Object logger(ProceedingJoinPoint joinPoint) throws Throwable {
		String signaterStr = joinPoint.getSignature().toString();
		System.out.println("시작 : " + signaterStr);
		
		//공통기능
		System.out.println("핵심 기능 전 실행 : " 
							+ System.currentTimeMillis());
		
		try {
			Object obj = joinPoint.proceed();
			return obj;
		}finally {
			System.out.println("핵심 기능 후 실행 : " 
					+ System.currentTimeMillis());
			System.out.println("끝 :" + signaterStr);
		}
		
	}
}

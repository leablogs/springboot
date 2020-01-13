package com.leablogs.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
//@Component
@Order(1)
public class UserAspect {
	@Pointcut("execution(* com.leablogs.service.impl.UserServiceImpl.*(..))")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void before() {
		System.out.println("before.......");
	}

	@After("pointcut()")
	public void after() {
		System.out.println("after.......");
	}

	@AfterReturning("pointcut()")
	public void afterReturning() {
		System.out.println("afterReturning.......");
	}

	@AfterThrowing("pointcut()")
	public void afterThrowing() {
		System.out.println("afterThrowing.......");
	}
}

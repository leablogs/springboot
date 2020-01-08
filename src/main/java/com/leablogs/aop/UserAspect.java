package com.leablogs.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
//@Component
@Order(1)
public class UserAspect {

	@Before("execution(* com.leablogs.service.impl.UserServiceImpl.getUser(..))")
	public void before() {
		System.out.println("before.......");
	}

	@After("execution(* com.leablogs.service.impl.UserServiceImpl.getUser(..))")
	public void after() {
		System.out.println("after.......");
	}

	@AfterReturning("execution(* com.leablogs.service.impl.UserServiceImpl.getUser(..))")
	public void afterReturning() {
		System.out.println("afterReturning.......");
	}

	@AfterThrowing("execution(* com.leablogs.service.impl.UserServiceImpl.getUser(..))")
	public void afterThrowing() {
		System.out.println("afterThrowing.......");
	}
}

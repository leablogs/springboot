package com.leablogs;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties.Packages;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.leablogs.aop.UserAspect;

@SpringBootApplication
public class SpringbootApplication {
	@Bean(name = "userAspect")
	protected UserAspect initAspect() {
		return new UserAspect();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}

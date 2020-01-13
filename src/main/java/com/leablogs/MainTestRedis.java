package com.leablogs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.leablogs.configure.RedisConfig;

public class MainTestRedis {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
		RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
		redisTemplate.opsForValue().set("key1", "value1");
		redisTemplate.opsForHash().put("hash", "field", "hvalue");
	}
}

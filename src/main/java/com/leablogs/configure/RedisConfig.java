package com.leablogs.configure;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

//@Configurable
public class RedisConfig {
	private RedisConnectionFactory redisConnectionFactory = null;

	@Bean(name = "RedisConnectionFactory")
	public RedisConnectionFactory initConnectionFactory() {
		if (this.redisConnectionFactory != null) {
			return this.redisConnectionFactory;
		}
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(30);
		poolConfig.setMaxTotal(50);
		poolConfig.setMaxWaitMillis(2000);

		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
		RedisStandaloneConfiguration rConfiguration = connectionFactory.getStandaloneConfiguration();
		rConfiguration.setHostName("192.168.182.128");
		rConfiguration.setPort(6379);
		// rConfiguration.setPassword("");
		this.redisConnectionFactory = connectionFactory;
		return connectionFactory;
	}

	// redis配置
	@Bean(name = "redisTemplate")
	public RedisTemplate<Object, Object> initRedisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		RedisSerializer StringRedisSerializer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(StringRedisSerializer);
		redisTemplate.setHashKeySerializer(StringRedisSerializer);
		redisTemplate.setHashValueSerializer(StringRedisSerializer);
		redisTemplate.setConnectionFactory(initConnectionFactory());
		return redisTemplate;
	}
}

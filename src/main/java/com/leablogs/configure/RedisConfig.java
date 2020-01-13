package com.leablogs.configure;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import redis.clients.jedis.JedisPoolConfig;

//@Configurable
public class RedisConfig {
//	@Autowired
	private RedisConnectionFactory redisConnectionFactory = null;
	@Autowired
	private RedisConnectionFactory connectionFactory = null;

//	@Bean(name = "RedisConnectionFactory")
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

	@Bean(name = "redisCacheManager")
	public RedisCacheManager initRedisCache() {
		RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		config = config.serializeValuesWith(SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));
		config = config.disableKeyPrefix();
		config = config.entryTtl(Duration.ofMinutes(10));
		RedisCacheManager redisCacheConfiguration = new RedisCacheManager(writer, config);
		return redisCacheConfiguration;
	}
}

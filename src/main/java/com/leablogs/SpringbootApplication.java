package com.leablogs;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.annotation.Aspect;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.leablogs.aop.UserAspect;
import com.leablogs.dao.UserMapper;

@SpringBootApplication
@MapperScan(basePackages = "com.leablogs.*", annotationClass = Repository.class, sqlSessionFactoryRef = "sqlSessionFactory", sqlSessionTemplateRef = "sqlSessionTemplate"
//		,markerInterface = "",basePackageClasses = "",factoryBean = "",nameGenerator = "",value = ""
)
@EnableCaching
public class SpringbootApplication {
//	@Bean
//	public MapperScannerConfigurer mapperScannerConfigurer() {
//		// 定义扫描器实例
//		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//		// 加载sqlsession
//		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//		// 定义扫描包
//		mapperScannerConfigurer.setBasePackage("com.leablogs.*");
//		// 限定被标注@repository接口才扫描
//		mapperScannerConfigurer.setAnnotationClass(Repository.class);
//		// mapperScannerConfigurer.setMarkerInterface();
//		return mapperScannerConfigurer;
//	}

//	@Autowired
//	SqlSessionFactory sqlSessionFactory = null;
//
//	@Bean
//	public MapperFactoryBean<UserMapper> initUserMapper() {
//		MapperFactoryBean<UserMapper> bean = new MapperFactoryBean<UserMapper>();
//		bean.setMapperInterface(UserMapper.class);
//		bean.setSqlSessionFactory(sqlSessionFactory);
//		return bean;
//	}
	@Autowired
	private RedisTemplate redisTemplate = null;
	@Autowired
	private RedisConnectionFactory RedisConnectionFactory = null;

	@PostConstruct
	public void init() {
		initRedisTemplate();
	}

	// redis 序列号
	private void initRedisTemplate() {
		RedisSerializer redisSerializer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(redisSerializer);
		redisTemplate.setHashKeySerializer(redisSerializer);
		redisTemplate.setHashValueSerializer(redisSerializer);
	}

	// Redis消息监听器
//	@Autowired
	private MessageListener redisMsgListener = null;

	private ThreadPoolTaskScheduler taskScheduler = null;

//	@Bean
	public ThreadPoolTaskScheduler initTaskScheduler() {
		if (taskScheduler != null) {
			return taskScheduler;
		}
		taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(20);
		return taskScheduler;
	}

//	@Bean
	public RedisMessageListenerContainer initContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(RedisConnectionFactory);
		container.setTaskExecutor(taskScheduler);
		Topic topic = new ChannelTopic("topic1");
		container.addMessageListener(redisMsgListener, topic);
		return container;
	}

	@Bean(name = "userAspect")
	protected UserAspect initAspect() {
		return new UserAspect();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}

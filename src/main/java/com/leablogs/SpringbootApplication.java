package com.leablogs;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.annotation.Aspect;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties.Packages;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.leablogs.aop.UserAspect;
import com.leablogs.dao.UserMapper;

@SpringBootApplication
@MapperScan(basePackages = "com.leablogs.*", annotationClass = Repository.class, sqlSessionFactoryRef = "sqlSessionFactory", sqlSessionTemplateRef = "sqlSessionTemplate"
//		,markerInterface = "",basePackageClasses = "",factoryBean = "",nameGenerator = "",value = ""
)
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

	@Bean(name = "userAspect")
	protected UserAspect initAspect() {
		return new UserAspect();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}

package com.leablogs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leablogs.dao.UserMapper;
import com.leablogs.pojo.User;
import com.leablogs.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper = null;

	@Override
//	@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
	@Transactional
	@Cacheable(value = "redisCacheManager", key = "'redis_user_'+#id")
	public User getUser(int id) {

		return userMapper.getUser(id);
	}

	@Override
	@Transactional
	@CachePut(value = "redisCacheManager", condition = "#result!='null'", key = "'redis_users_'+#userName")
	public List<User> getUsers(String userName) {
		return userMapper.getUsers();
	}

	@Override
//	@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1, propagation = Propagation.NESTED)
	@Transactional
	@CachePut(value = "redisCacheManager", condition = "#result != 'null'", key = "'redis_user_'+#result.id")
	public int insertUser(User user) {

		return userMapper.insertUser(user);
	}

	@Override
	public List<User> getAll() {
		return userMapper.getAll();
	}

}

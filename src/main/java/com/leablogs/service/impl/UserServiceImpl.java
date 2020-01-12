package com.leablogs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leablogs.dao.UserMapper;
import com.leablogs.pojo.User;
import com.leablogs.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper = null;

	@Override
	public User getUser(int id) {

		return userMapper.getUser(id);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}

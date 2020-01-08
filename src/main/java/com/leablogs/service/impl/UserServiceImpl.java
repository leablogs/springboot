package com.leablogs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leablogs.pojo.User;
import com.leablogs.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public User getUser(int id, String name) {

		User user = new User();
		user.setId(id);
		user.setNote("aaa");
		user.setUsername(name);
		return user;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}

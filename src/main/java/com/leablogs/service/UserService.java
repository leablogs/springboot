package com.leablogs.service;

import java.util.List;

import com.leablogs.pojo.User;

public interface UserService {
	public User getUser(int id, String name);

	public List<User> getUsers();
}

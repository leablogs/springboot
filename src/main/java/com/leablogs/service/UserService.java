package com.leablogs.service;

import java.util.List;

import com.leablogs.pojo.User;

public interface UserService {
	public User getUser(int id);

	public List<User> getUsers(String userName);

	public int insertUser(User user);

	public List<User> getAll();
}

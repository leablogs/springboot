package com.leablogs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leablogs.pojo.User;

@Repository
public interface UserMapper {
	public User getUser(int id);

	public List<User> getUsers();

	public int insertUser(User user);

	public List<User> getAll();
}

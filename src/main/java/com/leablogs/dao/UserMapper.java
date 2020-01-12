package com.leablogs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leablogs.pojo.User;

@Repository
public interface UserMapper {
	public User getUser(int id);

	public int insertUser(User user);
}

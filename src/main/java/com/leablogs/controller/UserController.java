package com.leablogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leablogs.pojo.User;
import com.leablogs.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/getUser")
	@ResponseBody
	public User getUser(Integer id, String name) {
		User user = userService.getUser(id, name);
		return user;
	}

}

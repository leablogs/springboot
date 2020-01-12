package com.leablogs.controller;

import java.util.HashMap;
import java.util.Map;

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
	public User getUser(Integer id) {
		User user = userService.getUser(id);
		return user;
	}

	@RequestMapping("/insertUser")
	@ResponseBody
	public Map insertUser(String userName, String note) {
		User user = new User();
		user.setNote(note);
		user.setUsername(userName);
		int update = userService.insertUser(user);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", update == 1);
		result.put("user", user);
		return result;
	}

}

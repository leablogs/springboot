package com.leablogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leablogs.pojo.User;
import com.leablogs.service.UserService;

@Controller
@RequestMapping("/interceptor")
public class Interceptor {
	@Autowired
	private UserService userService = null;

	@GetMapping("/getUser")
	public String getUser(@RequestParam(value = "id", required = true) Integer id) {
		System.out.println(id);
		User user = userService.getUser(id);
		return "/interceptor/welcome";
	}
}

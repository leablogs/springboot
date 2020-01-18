package com.leablogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leablogs.pojo.User;
import com.leablogs.service.UserService;

@RequestMapping("/data")
@Controller
public class DataModelController {
	@Autowired
	private UserService userService = null;

	@RequestMapping("userModel")
	public String userModle(Integer id, Model model) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
		return "data/user";
	}

	@RequestMapping("usermav")
	public ModelAndView userModelAndView(int id, ModelAndView mv) {
		User user = userService.getUser(id);
		mv.addObject("user", user);
		mv.setViewName("data/user");
		return mv;
	}
}

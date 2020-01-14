package com.leablogs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leablogs.pojo.User;
import com.leablogs.pojo.User1;
import com.leablogs.service.UserService;
import com.leablogs.validator.UserValidator;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;

	// 绑定自定义验证器
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidator());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
	}

	@RequestMapping("/getUser/{id}")
	@ResponseBody
	public User getUser(@PathVariable("id") Integer id) {
		User user = userService.getUser(id);
		return user;
	}

	@RequestMapping("/insertUser")
	@ResponseBody
//	public Map insertUser(String userName, String note) {
	public Map insertUser(@RequestParam(value = "userName", required = false) String userName,
			@RequestParam("note") String note) {
		User user = new User();
		user.setNote(note);
		user.setUsername(userName);
		int update = userService.insertUser(user);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", update == 1);
		result.put("user", user);
		return result;
	}

	@RequestMapping(value = "requestArr", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> requestArr(int[] intArr, Long[] longArr, String[] strArr) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("intArr", intArr);
		paramsMap.put("longArr", longArr);
		paramsMap.put("strArr", strArr);
		return paramsMap;
	}

	@RequestMapping("/add")
	public String add() {
		return "/user/add";
	}

	@PostMapping("insert")
	@ResponseBody
	public User insert(@RequestBody User user) {
		return user;
	}

	@GetMapping("/format")
	@ResponseBody
	public Map<String, Object> format(@DateTimeFormat(iso = ISO.DATE) Date date,
			@NumberFormat(pattern = "#,###.##") Double number) {
		Map<String, Object> formatMap = new HashMap<String, Object>();
		formatMap.put("date", date);
		formatMap.put("number", number);
		return formatMap;
	}

	@RequestMapping("/check")
	public String check() {
		return "/user/valid";
	}

	@PostMapping("/pojocheck")
	@ResponseBody
	public Map<String, Object> getUser1(@Valid @RequestBody User1 user1, Errors errors) {
		Map<String, Object> checkMap = new HashMap<String, Object>();
		List<ObjectError> oes = errors.getAllErrors();
		for (ObjectError oe : oes) {
			String key = null;
			String msg = null;
			if (oe instanceof FieldError) {
				FieldError fe = (FieldError) oe;
				key = fe.getField();
			} else {
				key = oe.getObjectName();
			}
			msg = oe.getDefaultMessage();
			checkMap.put(key, msg);
		}
		return checkMap;
	}
}

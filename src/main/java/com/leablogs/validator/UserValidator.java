package com.leablogs.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.leablogs.pojo.User1;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(User1.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (target == null) {
			errors.reject("", null, "username is not null");
			return;
		}
		User1 user1 = (User1) target;
		if (StringUtils.isEmpty(user1.getUsername())) {
			errors.rejectValue("username", null, "username is not null");
		}
	}

}

package com.wanying.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wanying.entity.User;
import com.wanying.facade.UserFacade;
import com.wanying.service.UserService;

@Component
public class DefaultUserFacade implements UserFacade {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean registerUser(String username, String password) {
		User user = new User();
		user.setId(username);
		user.setPassword(password);	
		return userService.registerUser(user);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}

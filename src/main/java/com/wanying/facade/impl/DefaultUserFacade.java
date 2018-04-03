package com.wanying.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wanying.ConverterUtil;
import com.wanying.dto.UserDTO;
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
	
	@Override
	public UserDTO doLogin(String username, String password) {
		User user = userService.getUser(username);
		if(user!=null) {
			if(password.equals(user.getPassword())) {
				return ConverterUtil.convertUserDTO(user);
			}
		}
		return null;
	}
	
	@Override
	public UserDTO updateUserDetail(String username, String shippingAddress, String paymentMethod) {
		User user = userService.updateUserDetail(username, shippingAddress, paymentMethod);
		return ConverterUtil.convertUserDTO(user);
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}

package com.wanying.facade.impl;

import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	public List<UserDTO> getAllUsersForAdmin() {
		List<User> users = userService.getAllUserForAdin();
		List<UserDTO> result = new ArrayList<UserDTO>();
		for(User user:users) {
			result.add(ConverterUtil.convertUserDTO(user));
		}
		return result;
	}
	

	@Override
	public UserDTO getUserById(String username) {
		User user = userService.getUser(username);
		if(user!=null) {
			return ConverterUtil.convertUserDTO(user);
		}else {
			return null;
		}
	}


	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


}

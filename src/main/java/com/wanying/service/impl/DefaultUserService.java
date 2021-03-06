package com.wanying.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanying.dao.UserDao;
import com.wanying.entity.Orders;
import com.wanying.entity.User;
import com.wanying.service.UserService;

@Service
public class DefaultUserService implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean registerUser(User user) {
		try {
			userDao.createUser(user);
			return true;
		}catch(Exception e) {
			return false;
		}	
	}
	
	@Override
	public User updateUserDetail(String username, String shippingAddress, String paymentMethod) {
		return userDao.updateUser(username, shippingAddress, paymentMethod);
	}
	
	@Override
	public User getUser(String id) {
		return userDao.getUserById(id);
	}
	
	@Override
	public void addOrderToUser(User user, Orders orders) {
		userDao.addOrderToUser(user, orders);
	}
	

	@Override
	public List<User> getAllUserForAdin() {
		List<User> allUsers = new ArrayList<>(userDao.getAllUser());
		List<User> result = allUsers.stream().filter(user->!"admin".equals(user.getId())).collect(Collectors.toList());
		return result;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


}

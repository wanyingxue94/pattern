package com.wanying.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanying.dao.UserDao;
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

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}

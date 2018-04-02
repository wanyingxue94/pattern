package com.wanying.dao;

import com.wanying.entity.User;

public interface UserDao {
	
	User getUserById(String id);
	
	void createUser(User user);
}

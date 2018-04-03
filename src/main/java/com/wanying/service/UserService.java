package com.wanying.service;

import com.wanying.entity.User;

public interface UserService {

	boolean registerUser(User user);
	
	User getUser(String id);
	
	User updateUserDetail(String username, String shippingAddress, String paymentMethod);
	
}

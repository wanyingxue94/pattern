package com.wanying.dao;

import java.util.List;

import com.wanying.entity.Orders;
import com.wanying.entity.User;

public interface UserDao {
	
	User getUserById(String id);
	
	void createUser(User user);
	
	User updateUser(String username, String shippingAddress, String paymentMethod);
	
    void addOrderToUser(User user,Orders order) ;
    
    List<User> getAllUser();
}

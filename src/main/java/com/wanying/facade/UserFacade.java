package com.wanying.facade;

import com.wanying.dto.UserDTO;

public interface UserFacade {
	
	boolean registerUser(String username,String password);
	
	UserDTO doLogin(String username,String password);
	
	UserDTO updateUserDetail(String username, String shippingAddress, String paymentMethod);
}

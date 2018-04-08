package com.wanying.facade;

import java.util.List;

import com.wanying.dto.UserDTO;

public interface UserFacade {
	
	boolean registerUser(String username,String password);
	
	UserDTO doLogin(String username,String password);
	
	UserDTO updateUserDetail(String username, String shippingAddress, String paymentMethod);
	
	List<UserDTO> getAllUsersForAdmin();
	
	UserDTO getUserById(String username);
}

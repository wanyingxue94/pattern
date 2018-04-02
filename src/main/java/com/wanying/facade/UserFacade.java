package com.wanying.facade;

public interface UserFacade {
	
	boolean registerUser(String username,String password);
	
	boolean doLogin(String username,String password);
}

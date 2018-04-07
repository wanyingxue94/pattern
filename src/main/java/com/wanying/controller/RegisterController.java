package com.wanying.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wanying.facade.UserFacade;

@Controller

public class RegisterController {
	
	@Autowired
	private UserFacade userFacade;

	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String welcome() {
		return "register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String doRegister(HttpServletRequest request,
			@RequestParam(value="username", required=false) String username, 
			@RequestParam(value="password", required=false) String password) {
		if(userFacade.registerUser(username, password)) {
			return "redirect:/welcome";
		}else {
			return "register";
		}
	}

	public UserFacade getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}
	
	
}

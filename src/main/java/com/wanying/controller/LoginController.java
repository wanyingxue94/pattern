package com.wanying.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wanying.dto.UserDTO;
import com.wanying.facade.UserFacade;

@Controller
public class LoginController {
	
	@Autowired
	private UserFacade userFacade;
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String doLogin(HttpServletRequest request,
			@RequestParam(value="username", required=false) String username, 
			@RequestParam(value="password", required=false) String password) {
		UserDTO dto = userFacade.doLogin(username, password);
		if(dto!=null) {
			request.getSession().setAttribute("currentUser",dto);
			return "redirect:books";
		}else {
			return "redirect:welcome";
		}	
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	public String doLogout(HttpServletRequest request) {
		if(request.getSession().getAttribute("currentUser")!=null) {
			UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");
			request.getSession().setAttribute("currentUser",null);
			request.getSession().setAttribute("sessionCart",null);
			if(currentUser.getUsername().equals("admin")) {
				return "redirect:/backoffice";
			}else {
				return "redirect:/welcome";
			}
		}else {
			return "redirect:/welcome";
		}
	}

	public UserFacade getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

}

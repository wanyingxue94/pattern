package com.wanying.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wanying.dto.UserDTO;
import com.wanying.facade.UserFacade;

@Controller
public class MyAccountController {
	
	@Autowired
	private UserFacade userFacade;

	@RequestMapping(value="/myaccount", method = RequestMethod.GET)
	public String welcome(HttpServletRequest request,Model model) {
		UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");
		if(currentUser!=null) {
			model.addAttribute("currentUser", currentUser);
			return "myaccount";
		}else {
			return "redirect:welcome";
		}
		
	}
	
	@RequestMapping(value="/edit-myaccount", method = RequestMethod.POST)
	public String editMyAccount(HttpServletRequest request,Model model,
			@RequestParam(value="shippingAddress", required=false) String shippingAddress, 
			@RequestParam(value="paymentMethod", required=false) String paymentMethod) {
		
		UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");
		String username = currentUser.getUsername();
		UserDTO updateUser = userFacade.updateUserDetail(username, shippingAddress, paymentMethod);
		request.getSession().setAttribute("currentUser",updateUser);
		model.addAttribute("currentUser", updateUser);
		return "redirect:myaccount";
	}

	public UserFacade getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}
	
	
}

package com.wanying.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wanying.dto.CartDTO;
import com.wanying.dto.OrderDTO;
import com.wanying.facade.OrderFacade;

@Controller
public class OrderController {

	@Autowired
	private OrderFacade orderFacade;
	
	@RequestMapping(value="/order/placeOrder", method = RequestMethod.POST)
	public String placeOrder(HttpServletRequest request,Model model) {
		if(request.getSession().getAttribute("sessionCart")!=null&&request.getSession().getAttribute("currentUser")!=null) {
			CartDTO cart = (CartDTO) request.getSession().getAttribute("sessionCart");
			OrderDTO order = orderFacade.placeOrder(cart);
			if(order!=null) {
				 request.getSession().setAttribute("placedOrder",order);
				 request.getSession().setAttribute("sessionCart",null);
				 return "redirect:/orderconfirmation";
			}else {
				 return "redirect: cart";
			}
		}
		return null;		
	}
	
	@RequestMapping(value="/orderconfirmation", method = RequestMethod.GET)
	public String orderConfirmation(HttpServletRequest request,Model model) {
		if(request.getSession().getAttribute("placedOrder")!=null&&request.getSession().getAttribute("currentUser")!=null) {
			OrderDTO order = (OrderDTO)request.getSession().getAttribute("placedOrder");
			model.addAttribute("order",order);
			return "orderconfirmation";
		}else {
			return "redirect: welcome";
		}
	}
	
	@RequestMapping(value="/order/user/{username}", method = RequestMethod.GET)
	public String getOrderForUser(HttpServletRequest request,Model model,@PathVariable("username") String username) {
		if(request.getSession().getAttribute("currentUser") ==null) {
			return "redirect:/backoffice";
		}else {
			List<OrderDTO> orders = orderFacade.getOrdersByUser(username);
			model.addAttribute("orders",orders);
			return "backofficeuserorders";
		}		
	}

	public OrderFacade getOrderFacade() {
		return orderFacade;
	}

	public void setOrderFacade(OrderFacade orderFacade) {
		this.orderFacade = orderFacade;
	}
	
	
	
}

package com.wanying.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wanying.dto.CartDTO;
import com.wanying.dto.UserDTO;
import com.wanying.facade.CartFacade;

@Controller
public class CartController {
	
	@Autowired
	private CartFacade cartFacade;

	@RequestMapping(value="/cart/add", method = RequestMethod.POST)
	public String addToCart(HttpServletRequest request,Model model,
			@RequestParam(value="quantityToAdd", required=false) int quantityToAdd, 
			@RequestParam(value="bookId", required=false) int bookId) {
		if(request.getSession().getAttribute("currentUser") ==null) {
			return "redirect:/welcome";
		}else {
			if(request.getSession().getAttribute("sessionCart")!=null) {
				CartDTO sessionCart = (CartDTO) request.getSession().getAttribute("sessionCart");
				int cartId = sessionCart.getId();
				CartDTO updatedCart = cartFacade.addToCart(cartId, bookId, quantityToAdd);
				request.getSession().setAttribute("sessionCart",updatedCart);
				model.addAttribute("cart", updatedCart);
			}else {
				UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");
				String username = currentUser.getUsername();
				CartDTO newCart = cartFacade.addToNewCart(username,bookId, quantityToAdd);
				request.getSession().setAttribute("sessionCart",newCart);
				model.addAttribute("cart", newCart);
			}
			return "redirect:/cart";	
			}
	}
	
	@RequestMapping(value="/cart", method = RequestMethod.GET)
	public String welcome(HttpServletRequest request,Model model) {
		CartDTO sessionCart = (CartDTO) request.getSession().getAttribute("sessionCart");
		if(sessionCart!=null) {
			model.addAttribute("cart", sessionCart);
			return "cart";
		}else {
			return "redirect:books";
		}
		
	}

	public CartFacade getCartFacade() {
		return cartFacade;
	}

	public void setCartFacade(CartFacade cartFacade) {
		this.cartFacade = cartFacade;
	}
	
}

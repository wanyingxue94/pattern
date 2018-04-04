package com.wanying.facade.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wanying.ConverterUtil;
import com.wanying.dto.CartDTO;
import com.wanying.dto.OrderDTO;
import com.wanying.entity.Cart;
import com.wanying.entity.Orders;
import com.wanying.entity.User;
import com.wanying.facade.OrderFacade;
import com.wanying.service.CartService;
import com.wanying.service.OrderService;
import com.wanying.service.StockService;
import com.wanying.service.UserService;

@Component
public class DefaultOrderFacade implements OrderFacade {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	@Override
	public OrderDTO placeOrder(CartDTO cartDTO) {
		int cartId = cartDTO.getId();
		Cart cart = cartService.getCartById(cartId);
		User user = userService.getUser(cartDTO.getUser().getUsername());
		if(stockService.updateStock(cart)) {
			Orders order = orderService.placeOrder(cart);
			userService.addOrderToUser(user, order);
			return ConverterUtil.convertOrder(order);
		}else {
			return null;
		}
	}

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public StockService getStockService() {
		return stockService;
	}

	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
}

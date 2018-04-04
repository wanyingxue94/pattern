package com.wanying.service;

import com.wanying.entity.Cart;
import com.wanying.entity.Orders;

public interface OrderService {

	Orders placeOrder(Cart cart);
	
}

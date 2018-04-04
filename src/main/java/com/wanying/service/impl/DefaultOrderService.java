package com.wanying.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanying.dao.OrderDao;
import com.wanying.entity.Cart;
import com.wanying.entity.Entry;
import com.wanying.entity.Orders;
import com.wanying.service.OrderService;

@Service
public class DefaultOrderService implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public Orders placeOrder(Cart cart) {
		Orders order = new Orders();
		order.setUser(cart.getUser());
		order.setTotalPrice(cart.getTotalPrice());
		Set<Entry> entries = new HashSet<>(cart.getEntries());
		order.setEntries(entries);	
		return orderDao.createOrder(order);
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	

}

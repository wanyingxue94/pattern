package com.wanying.facade;

import java.util.List;

import com.wanying.dto.CartDTO;
import com.wanying.dto.OrderDTO;

public interface OrderFacade {

	OrderDTO placeOrder(CartDTO cart);
	
	List<OrderDTO> getOrdersByUser(String username);
	
}

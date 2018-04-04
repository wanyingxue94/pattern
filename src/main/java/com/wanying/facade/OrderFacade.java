package com.wanying.facade;

import com.wanying.dto.CartDTO;
import com.wanying.dto.OrderDTO;

public interface OrderFacade {

	OrderDTO placeOrder(CartDTO cart);
	
}

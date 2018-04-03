package com.wanying.facade;

import com.wanying.dto.CartDTO;

public interface CartFacade {
	
	CartDTO addToNewCart(String username,int bookId,int quantity);
	
	CartDTO addToCart(int cartId,int bookId, int quantity);

}

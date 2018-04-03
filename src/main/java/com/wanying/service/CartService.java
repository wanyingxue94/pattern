package com.wanying.service;

import com.wanying.entity.Cart;
import com.wanying.entity.Entry;
import com.wanying.entity.User;

public interface CartService {

	Cart createNewCart(User user);
	
	Cart addToCart(Cart cart,Entry entry);
	
	void calculateCart(Cart cart);
	
	Cart getCartById(int id);
}

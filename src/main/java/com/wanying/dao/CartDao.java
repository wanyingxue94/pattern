package com.wanying.dao;

import com.wanying.entity.Cart;
import com.wanying.entity.User;

public interface CartDao {

	Cart createNewCart(User user);
	
	Cart getCartById(int id);
}

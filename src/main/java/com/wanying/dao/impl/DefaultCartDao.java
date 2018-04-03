package com.wanying.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wanying.dao.CartDao;
import com.wanying.entity.Cart;
import com.wanying.entity.User;

@Transactional
@Repository
public class DefaultCartDao implements CartDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Cart createNewCart(User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		entityManager.persist(cart);
		return cart;
	}

	@Override
	public Cart getCartById(int id) {
		return entityManager.find(Cart.class, id);
	}

}

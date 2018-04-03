package com.wanying.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanying.dao.CartDao;
import com.wanying.entity.Cart;
import com.wanying.entity.Entry;
import com.wanying.entity.User;
import com.wanying.service.CartService;

@Service
public class DefaultCartService implements CartService {
	
	@Autowired
	private CartDao cartDao;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Cart createNewCart(User user) {
		return cartDao.createNewCart(user);
	}

	@Transactional
	@Override
	public Cart addToCart(Cart cart,Entry entry) {
		if(cart.getEntries()!=null) {
			cart.getEntries().add(entry);
		}else {
			Set<Entry> entries = new HashSet<Entry>();
			entries.add(entry);
			cart.setEntries(entries);
		}		
		entityManager.flush();
		calculateCart(cart);
		return cart;
	}

	@Transactional
	@Override
	public void calculateCart(Cart cart) {
		double totalPrice = 0.0;
		for(Entry entry:cart.getEntries()) {
			double entryTotal = entry.getBook().getPrice() * entry.getQuantity();
			totalPrice = totalPrice + entryTotal;
		}
		cart.setTotalPrice(totalPrice);
		entityManager.flush();
	}
	
	@Override
	public Cart getCartById(int id) {
		return cartDao.getCartById(id);
	}

	public CartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



}

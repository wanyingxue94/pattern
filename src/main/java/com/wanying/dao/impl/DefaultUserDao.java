package com.wanying.dao.impl;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wanying.dao.UserDao;
import com.wanying.entity.User;

@Transactional
@Repository
public class DefaultUserDao implements UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public User getUserById(String id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public void createUser(User user) {
		entityManager.persist(user);
	}

	@Override
	public User updateUser(String username, String shippingAddress, String paymentMethod) {
		User user = this.getUserById(username);
		user.setShippingAddress(shippingAddress);
		user.setPaymentMethod(paymentMethod);
		entityManager.flush();
		return user;
	}

}

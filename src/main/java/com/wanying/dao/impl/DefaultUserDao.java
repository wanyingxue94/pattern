package com.wanying.dao.impl;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wanying.dao.UserDao;
import com.wanying.entity.Orders;
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

	@Override
	public void addOrderToUser(User user,Orders order) {
		Set<Orders> orders = user.getOrders();
		if(orders!=null) {
			user.getOrders().add(order);		
		}else {
			Set<Orders> neworders = new HashSet<>();
			neworders.add(order);
			user.setOrders(neworders);
		}
		entityManager.flush();	
	}

	@Override
	public List<User> getAllUser() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
	}

}

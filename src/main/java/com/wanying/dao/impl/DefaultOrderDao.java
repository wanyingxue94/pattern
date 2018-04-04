package com.wanying.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wanying.dao.OrderDao;
import com.wanying.entity.Orders;

@Transactional
@Repository
public class DefaultOrderDao implements OrderDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Orders createOrder(Orders order) {
		entityManager.persist(order);
		return order;
	}

}

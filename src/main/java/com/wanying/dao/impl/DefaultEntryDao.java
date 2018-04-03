package com.wanying.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wanying.dao.EntryDao;
import com.wanying.entity.Entry;

@Transactional
@Repository
public class DefaultEntryDao implements EntryDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Entry createNewEntry(Entry entry) {
		entityManager.persist(entry);
		return entry;
	}

	@Override
	public void updateEntry(Entry entry) {
		Entry e = getEntryById(entry.getId());
		e.setQuantity(entry.getQuantity());
		entityManager.flush();
	}

	@Override
	public Entry getEntryById(int id) {
		return entityManager.find(Entry.class, id);
	}

}

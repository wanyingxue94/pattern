package com.wanying.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanying.dao.EntryDao;
import com.wanying.entity.Book;
import com.wanying.entity.Entry;
import com.wanying.service.EntryService;

@Service
public class DefaultEntryService implements EntryService {

	@Autowired
	private EntryDao entryDao;
	
	@Override
	public Entry createEntry(Book book, int quantity) {
		Entry newEntry = new Entry();
		newEntry.setBook(book);
		newEntry.setQuantity(quantity);
		return entryDao.createNewEntry(newEntry);
	}
	
	@Override
	public void updateEntry(Entry entry) {
		entryDao.updateEntry(entry);
	}

	public EntryDao getEntryDao() {
		return entryDao;
	}

	public void setEntryDao(EntryDao entryDao) {
		this.entryDao = entryDao;
	}

	
}

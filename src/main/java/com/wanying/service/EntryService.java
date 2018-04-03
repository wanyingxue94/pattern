package com.wanying.service;

import com.wanying.entity.Book;
import com.wanying.entity.Entry;

public interface EntryService {

	Entry createEntry(Book book,int quantity);
	
	void updateEntry(Entry entry);
}

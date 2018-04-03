package com.wanying.dao;

import com.wanying.entity.Entry;

public interface EntryDao {
	
	Entry createNewEntry(Entry entry);
	
	void updateEntry(Entry entry);
	
	Entry getEntryById(int id);
	
}

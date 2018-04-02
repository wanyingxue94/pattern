package com.wanying.strategy.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wanying.dao.BookDao;
import com.wanying.entity.Book;
import com.wanying.strategy.SearchBookStrategy;

@Component
public class SearchBookAuthorStrategy implements SearchBookStrategy {

	@Autowired
	private BookDao bookDao;
	
	@Override
	public List<Book> searchBook(String query) {
		return bookDao.searchBookOnAuthor(query);
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
}

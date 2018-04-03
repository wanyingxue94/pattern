package com.wanying.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanying.dao.BookDao;
import com.wanying.entity.Book;
import com.wanying.service.BookService;

@Service
public class DefaultBookService implements BookService {
	
	@Autowired
	private BookDao bookDao;
	
	@Override
	public List<Book> getAllBook() {	
		return bookDao.getAllBook();
	}
	
	@Override
	public Book getBookById(int id) {
		return bookDao.getBookById(id);
	}


	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}


	
}

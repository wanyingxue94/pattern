package com.wanying.dao;

import java.util.List;

import com.wanying.entity.Book;

public interface BookDao {

	Book getBookById(int id);
	
	void createUser(Book book);
	
	void updateStock(Book book,int stock);
	
	List<Book> getAllBook();
	
	List<Book> searchBookOnTopic(String searchKeyword);
	
	List<Book> searchBookOnAuthor(String searchKeyword);
	
	List<Book> searchBookOnTitle(String searchKeyword);
	
}

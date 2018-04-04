package com.wanying.dao;

import java.util.List;

import com.wanying.entity.Book;
import com.wanying.entity.Comment;

public interface BookDao {

	Book getBookById(int id);
	
	void createUser(Book book);
	
	void updateStock(Book book,int stock);
	
	List<Book> getAllBook();
	
	List<Book> searchBookOnTopic(String searchKeyword);
	
	List<Book> searchBookOnAuthor(String searchKeyword);
	
	List<Book> searchBookOnTitle(String searchKeyword);
	
	Comment createComment(Book book,int rate, String username, String comment);
	
	void addComment(Book book,Comment comment);
	
	void updateComment(Comment c,int rate,String comment);
	
}

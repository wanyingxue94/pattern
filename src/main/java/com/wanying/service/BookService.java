package com.wanying.service;

import java.util.List;

import com.wanying.entity.Book;
import com.wanying.entity.Comment;

public interface BookService {
	
	List<Book> getAllBook();
	
	Book getBookById(int id);
	
	void addNewComment(Book book,int rate, String comment, String username);
	
	void updateComment(Comment c, int rate, String comment);
	
	void updateStock(Book book, int updatedStock);

}

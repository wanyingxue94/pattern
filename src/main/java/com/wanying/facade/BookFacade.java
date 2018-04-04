package com.wanying.facade;

import java.util.List;

import com.wanying.dto.BookDTO;

public interface BookFacade {

	List<BookDTO> getAllBook();
	
	List<BookDTO> searchBook(String query, String searchOn);
	
	BookDTO getBookById(int id);
	
	void addComment(int bookId,int rate, String username,String comment);
}

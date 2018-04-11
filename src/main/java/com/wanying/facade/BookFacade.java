package com.wanying.facade;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wanying.dto.BookDTO;

public interface BookFacade {

	List<BookDTO> getAllBook();
	
	List<BookDTO> searchBook(String query, String searchOn);
	
	BookDTO getBookById(int id);
	
	void addComment(int bookId,int rate, String username,String comment);
	
	void updateBookStock(int bookId,int updatedStock);
	
	void addBook(MultipartFile file,String title,String author,String topic,int stock,String price);
}

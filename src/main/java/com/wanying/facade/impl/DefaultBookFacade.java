package com.wanying.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.wanying.ConverterUtil;
import com.wanying.dto.BookDTO;
import com.wanying.entity.Book;
import com.wanying.entity.Comment;
import com.wanying.facade.BookFacade;
import com.wanying.service.BookService;
import com.wanying.strategy.SearchBookStrategy;

@Component
public class DefaultBookFacade implements BookFacade {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private  BeanFactory beanFactory;
	
	@Override
	public List<BookDTO> getAllBook() {
		List<Book> books = bookService.getAllBook();
		List<BookDTO> result = new ArrayList<BookDTO>();
		if(!CollectionUtils.isEmpty(books)) {
			for(Book book: books) {
				result.add(ConverterUtil.convertBookDTO(book));
			}
		}
		return result;
	}
	
	@Override
	public List<BookDTO> searchBook(String query, String searchOn) {
		List<Book> books = this.doSearch(query, searchOn);
		List<BookDTO> result = new ArrayList<BookDTO>();
		if(!CollectionUtils.isEmpty(books)) {
			for(Book book: books) {
				result.add(ConverterUtil.convertBookDTO(book));
			}
		}
		return result;
	}
	
	@Override
	public BookDTO getBookById(int id) {
		Book book = bookService.getBookById(id);
		return ConverterUtil.convertBookDTO(book);
	}
	
	@Override
	public void addComment(int bookId, int rate, String username, String comment) {
		Book book = bookService.getBookById(bookId);
		if(CollectionUtils.isEmpty(book.getComment())) {
			bookService.addNewComment(book, rate, comment, username);
		}else {
			boolean hasComment = false;
			for(Comment c:book.getComment()) {
				if(c.getUsername().equals(username)) {
					hasComment = true;
					bookService.updateComment(c, rate, comment);
				}
			}
			if(!hasComment) {
				bookService.addNewComment(book, rate, comment, username);
			}
		}
	}

	
	private List<Book> doSearch(String query, String searchOn) {
		String stratgeyName = "";
		if(searchOn.equals("topic")) {
			stratgeyName = "searchBookTopicStrategy";
		}else if(searchOn.equals("title")) {
			stratgeyName = "searchBookTitleStrategy";
		}else {
			stratgeyName = "searchBookAuthorStrategy";
		}	
		SearchBookStrategy strategy = beanFactory.getBean(stratgeyName, SearchBookStrategy.class);
		return strategy.searchBook(query);
	}
	
	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}


}

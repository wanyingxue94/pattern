package com.wanying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wanying.dto.BookDTO;
import com.wanying.facade.BookFacade;

@Controller
public class BookController {

	@Autowired
	private BookFacade bookFacade;
	
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public String getAllBooks(Model model) {
		List<BookDTO> books = bookFacade.getAllBook();
		model.addAttribute("books", books);
		return "books";	
	}
	
	@RequestMapping(value="/books/search", method = RequestMethod.POST)
	public String searchBooks(Model model,
			@RequestParam(value="query", required=false) String query, 
			@RequestParam(value="searchOn", required=false) String searchOn) {
		List<BookDTO> books = bookFacade.searchBook(query, searchOn);
		model.addAttribute("books", books);
		return "books";	
	}

	public BookFacade getBookFacade() {
		return bookFacade;
	}

	public void setBookFacade(BookFacade bookFacade) {
		this.bookFacade = bookFacade;
	}
	
	
}

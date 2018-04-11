package com.wanying.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wanying.dto.BookDTO;
import com.wanying.dto.UserDTO;
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
	
	@RequestMapping(value="/books/add", method = RequestMethod.POST)
	public String addBook(Model model,HttpServletRequest request,
			@RequestParam(value="image", required=false) MultipartFile image, 
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="author", required=false) String author,
			@RequestParam(value="topic", required=false) String topic,
			@RequestParam(value="stock", required=false) int stock,
			@RequestParam(value="price", required=false) String price) {
		if(request.getSession().getAttribute("currentUser") ==null) {
			return "redirect:/backoffice";
		}else {
			UserDTO currentUser = (UserDTO)request.getSession().getAttribute("currentUser");
			if(currentUser.getUsername().equals("admin")) {
				bookFacade.addBook(image, title, author, topic, stock, price);
				return "redirect:/backoffice/books";
			}else {
				return "redirect:/backoffice";
			}
		}
	}
	
	public BookFacade getBookFacade() {
		return bookFacade;
	}

	public void setBookFacade(BookFacade bookFacade) {
		this.bookFacade = bookFacade;
	}
	
	
}

package com.wanying.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wanying.dto.BookDTO;
import com.wanying.facade.BookFacade;

@Controller
public class ImageController {
	
	@Autowired
	private BookFacade bookFacade;
	
	@RequestMapping(value = "/book/imageDisplay", method = RequestMethod.GET)
	 public void showImage(@RequestParam("id") Integer id, HttpServletResponse response,HttpServletRequest request) throws IOException {
		BookDTO book = bookFacade.getBookById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	    response.getOutputStream().write(book.getImage());
	    response.getOutputStream().close();
	}

	public BookFacade getBookFacade() {
		return bookFacade;
	}

	public void setBookFacade(BookFacade bookFacade) {
		this.bookFacade = bookFacade;
	}
	
	
}

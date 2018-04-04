package com.wanying.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wanying.dto.BookDTO;
import com.wanying.dto.CommentDTO;
import com.wanying.dto.UserDTO;
import com.wanying.facade.BookFacade;

@Controller
public class CommentController {
	
	@Autowired
	private BookFacade bookFacade;

	@RequestMapping(value="/edit-comment/{bookId}", method = RequestMethod.GET)
	public String comment(HttpServletRequest request,Model model,@PathVariable("bookId") int bookId) {
		UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");
		if(currentUser!=null) {
			BookDTO book = bookFacade.getBookById(bookId);
			model.addAttribute("book",book);
			setOldComment(book,model,currentUser);
			return "editcomment";
		}else {
			return "redirect:/welcome";
		}
		
	}
	
	@RequestMapping(value="/add-comment", method = RequestMethod.POST)
	public String addComment(HttpServletRequest request,Model model,
			@RequestParam(value="rate", required=false) int rate, 
			@RequestParam(value="bookId", required=false) int bookId,
			@RequestParam(value="comment", required=false) String comment) {
		UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");
		if(currentUser!=null) {
			String username = currentUser.getUsername();
			bookFacade.addComment(bookId, rate, username, comment);
			return "redirect:/books";
		}else {
			return "redirect:/welcome";
		}
		
	}
	
	@RequestMapping(value="/showcomments/{bookId}", method = RequestMethod.GET)
	public String showComment(HttpServletRequest request,Model model,@PathVariable("bookId") int bookId) {
		BookDTO book = bookFacade.getBookById(bookId);
		if(book!=null) {
			model.addAttribute("book",book);
			model.addAttribute("comments",book.getComments());
			return "showcomments";
		}else {
			return "redirect:/books";
		}
		
	}
	
	
	private void setOldComment(BookDTO book,Model model,UserDTO currentUser) {
		if(CollectionUtils.isEmpty(book.getComments())) {
			model.addAttribute("oldcomment", new CommentDTO());
		}else {
			boolean hasComment = false;
			for(CommentDTO comment:book.getComments()) {
				if(comment.getUsername().equals(currentUser.getUsername())) {
					model.addAttribute("oldcomment", comment);
					hasComment = true;
				}
			}
			if(!hasComment) {
				model.addAttribute("oldcomment", new CommentDTO());
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

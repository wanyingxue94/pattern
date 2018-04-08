package com.wanying.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wanying.dto.BookDTO;
import com.wanying.dto.UserDTO;
import com.wanying.facade.BookFacade;
import com.wanying.facade.UserFacade;

@Controller
public class BackofficeController {

	@Autowired
	private UserFacade userFacade;
	
	@Autowired
	private BookFacade bookFacade;
	
	@RequestMapping(value="/backoffice", method = RequestMethod.GET)
	public String backoffice() {
		return "backoffice";
	}
	
	
	@RequestMapping(value="/backoffice/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request,
			@RequestParam(value="username", required=false) String username, 
			@RequestParam(value="password", required=false) String password) {
		if(username.equals("admin")) {
			UserDTO dto = userFacade.doLogin(username, password);
			if(dto!=null) {	
				request.getSession().setAttribute("currentUser",dto);
				return "redirect:books";
			}
		}
		return "redirect:/backoffice";
	}
	
	
	@RequestMapping(value="/backoffice/books", method = RequestMethod.GET)
	public String backofficeBooks(HttpServletRequest request,Model model) {
		List<BookDTO> books = bookFacade.getAllBook();
		model.addAttribute("books", books);
		return "backofficebooks";
	}
	
	
	@RequestMapping(value="/backoffice/book/search", method = RequestMethod.POST)
	public String searchBooks(Model model,
			@RequestParam(value="query", required=false) String query, 
			@RequestParam(value="searchOn", required=false) String searchOn) {
		List<BookDTO> books = bookFacade.searchBook(query, searchOn);
		model.addAttribute("books", books);
		return "backofficebooks";
	}
	
	
	@RequestMapping(value="/backoffice/users", method = RequestMethod.GET)
	public String backofficeUsers(HttpServletRequest request,Model model) {
		List<UserDTO> users = userFacade.getAllUsersForAdmin();
		model.addAttribute("users", users);
		return "backofficeusers";
	}
	
	@RequestMapping(value="/backoffice/updateStock", method = RequestMethod.POST)
	public String searchBooks(Model model,HttpServletRequest request,
			@RequestParam(value="bookId", required=false) int bookId, 
			@RequestParam(value="updatedStock", required=false) int updatedStock) {
		if(request.getSession().getAttribute("currentUser") ==null) {
			return "redirect:/backoffice";
		}else {
			UserDTO currentUser = (UserDTO)request.getSession().getAttribute("currentUser");
			if(currentUser.getUsername().equals("admin")) {
				bookFacade.updateBookStock(bookId, updatedStock);
				return "redirect:/backoffice/books";
			}else {
				return "redirect:/backoffice";
			}
		}
		
	}
	
	@RequestMapping(value="/backoffice/users/search", method = RequestMethod.POST)
	public String searchUser(Model model,HttpServletRequest request,
			@RequestParam(value="username", required=false) String username) {
		if(request.getSession().getAttribute("currentUser") ==null) {
			return "redirect:/backoffice";
		}else {
			UserDTO currentUser = (UserDTO)request.getSession().getAttribute("currentUser");
			if(currentUser.getUsername().equals("admin")) {
				List<UserDTO> users = new ArrayList<UserDTO>();
				UserDTO user = userFacade.getUserById(username);
				if(user!=null&&!user.getUsername().equals("admin")) {
					users.add(user);
				}
				model.addAttribute("users", users);
				return "backofficeusers";
			}else {
				return "redirect:/backoffice";
			}
		}
		
	}

	public UserFacade getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	public BookFacade getBookFacade() {
		return bookFacade;
	}

	public void setBookFacade(BookFacade bookFacade) {
		this.bookFacade = bookFacade;
	}
	
	
	
}

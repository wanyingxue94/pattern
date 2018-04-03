package com.wanying.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.wanying.ConverterUtil;
import com.wanying.dto.CartDTO;
import com.wanying.entity.Book;
import com.wanying.entity.Cart;
import com.wanying.entity.Entry;
import com.wanying.entity.User;
import com.wanying.facade.CartFacade;
import com.wanying.service.BookService;
import com.wanying.service.CartService;
import com.wanying.service.EntryService;
import com.wanying.service.UserService;

@Component
public class DefaultCartFacade implements CartFacade{

	@Autowired
	private EntryService entryService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public CartDTO addToNewCart(String username,int bookId, int quantity) {
		User user = userService.getUser(username);
		Book book = bookService.getBookById(bookId);
		Cart cart = cartService.createNewCart(user);
		Entry entry = entryService.createEntry(book, quantity);
		Cart newCart = cartService.addToCart(cart, entry);
		return ConverterUtil.convertCart(newCart);
	}

	@Override
	public CartDTO addToCart(int cartId, int bookId, int quantity) {
		Cart cart = cartService.getCartById(cartId);
		doAdd(cart,bookId,quantity);
		return ConverterUtil.convertCart(cartService.getCartById(cartId));
	}
	
	private void doAdd(Cart cart, int bookId, int quantity) {
		if(CollectionUtils.isEmpty(cart.getEntries())) {
			Book book = bookService.getBookById(bookId);
			Entry entry = entryService.createEntry(book, quantity);
			cartService.addToCart(cart, entry);
		}else {
			boolean contain = false;
			for(Entry entry:cart.getEntries()) {
				if(entry.getBook().getId() == bookId) {
					contain = true;
					int q = entry.getQuantity();
					entry.setQuantity(q+quantity);
					entryService.updateEntry(entry);
					cartService.calculateCart(cart);
				}
			}
			if(!contain) {
				Book book = bookService.getBookById(bookId);
				Entry entry = entryService.createEntry(book, quantity);
				cartService.addToCart(cart, entry);
			}
		}
	}

	public EntryService getEntryService() {
		return entryService;
	}

	public void setEntryService(EntryService entryService) {
		this.entryService = entryService;
	}

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	

}

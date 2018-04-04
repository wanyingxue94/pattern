package com.wanying;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.wanying.dto.BookDTO;
import com.wanying.dto.CartDTO;
import com.wanying.dto.CommentDTO;
import com.wanying.dto.EntryDTO;
import com.wanying.dto.OrderDTO;
import com.wanying.dto.UserDTO;
import com.wanying.entity.Book;
import com.wanying.entity.Cart;
import com.wanying.entity.Comment;
import com.wanying.entity.Entry;
import com.wanying.entity.Orders;
import com.wanying.entity.User;

public class ConverterUtil {

	public static BookDTO convertBookDTO(Book book) {
		BookDTO dto = new BookDTO();
		dto.setAuthor(book.getAuthor());
		dto.setStock(book.getStock());
		dto.setTitle(book.getTitle());
		dto.setTopic(book.getTopic());
		dto.setPrice(book.getPrice());
		dto.setId(book.getId());
		List<CommentDTO> comments = new ArrayList<CommentDTO>();
		for(Comment comment:book.getComment()) {
			comments.add(convertComment(comment));
		}
		dto.setComments(comments);
		if(CollectionUtils.isEmpty(comments)) {
			dto.setRating(0);
		}else {
			int total = 0;
			for(Comment comment:book.getComment()) {
				total = total + comment.getRate();
			}
			float rating = (float) total/book.getComment().size();
			dto.setRating(rating);
		}
		return dto;
	}
	
	
	public static UserDTO convertUserDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setUsername(user.getId());
		dto.setPaymentMethod(user.getPaymentMethod());
		dto.setShippingAddress(user.getShippingAddress());
		return dto;
	}
	
	public static EntryDTO convertEntryDTO(Entry entry) {
		EntryDTO dto = new EntryDTO();
		dto.setBook(convertBookDTO(entry.getBook()));
		dto.setId(entry.getId());
		dto.setQuantity(entry.getQuantity());
		dto.setPrice(entry.getQuantity()*entry.getBook().getPrice());
		return dto;
	}
	
	public static CartDTO convertCart(Cart cart) {
		CartDTO dto = new CartDTO();
		dto.setId(cart.getId());
		dto.setUser(convertUserDTO(cart.getUser()));
		dto.setTotalPrice(cart.getTotalPrice());
		List<EntryDTO> entries = new ArrayList<EntryDTO>();
		for(Entry entry:cart.getEntries()) {
			entries.add(convertEntryDTO(entry));
		}
		dto.setEntries(entries);
		return dto;
	}
	
	public static OrderDTO convertOrder(Orders order) {
		OrderDTO dto = new OrderDTO();
		dto.setId(order.getId());
		dto.setUser(convertUserDTO(order.getUser()));
		dto.setTotalPrice(order.getTotalPrice());
		List<EntryDTO> entries = new ArrayList<EntryDTO>();
		for(Entry entry:order.getEntries()) {
			entries.add(convertEntryDTO(entry));
		}
		dto.setEntries(entries);
		return dto;
	}
	
	public static CommentDTO convertComment(Comment comment) {
		CommentDTO dto = new CommentDTO();
		dto.setComment(comment.getComment());
		dto.setId(comment.getId());
		dto.setRate(comment.getRate());
		dto.setUsername(comment.getUsername());
		return dto;
	}
	
}

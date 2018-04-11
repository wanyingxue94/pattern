package com.wanying.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.StringUtils;
import com.wanying.dao.BookDao;
import com.wanying.entity.Book;
import com.wanying.entity.Comment;
import com.wanying.service.BookService;

@Service
public class DefaultBookService implements BookService {
	
	@Autowired
	private BookDao bookDao;
	
	@Override
	public List<Book> getAllBook() {	
		return bookDao.getAllBook();
	}
	
	@Override
	public Book getBookById(int id) {
		return bookDao.getBookById(id);
	}
	

	@Override
	public void addNewComment(Book book, int rate, String comment, String username) {
		Comment newComment = bookDao.createComment(book, rate, username, comment);
		bookDao.addComment(book, newComment);
	}

	@Override
	public void updateComment(Comment c, int rate, String comment) {
		bookDao.updateComment(c, rate, comment);
	}

	@Override
	public void updateStock(Book book, int updatedStock) {
		bookDao.updateStockForAdmin(book,updatedStock);
	}
	
	@Override
	public void createNewBook(MultipartFile file, String title, String author, String topic, int stock, String price) {
		try {
				byte[] imageByte = file!=null?file.getBytes():null;
				double bookPrice = StringUtils.isNullOrEmpty(price)?0.0:Double.parseDouble(price);
				Book book = new Book();
				book.setAuthor(author);
				book.setBookImage(imageByte);
				book.setTitle(title);
				book.setPrice(bookPrice);
				book.setTopic(topic);
				book.setStock(stock);
				bookDao.createBook(book);
			} catch (IOException e) {
				e.printStackTrace();
			}	
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}




}

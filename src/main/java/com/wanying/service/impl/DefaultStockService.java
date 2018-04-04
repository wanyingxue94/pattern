package com.wanying.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanying.dao.BookDao;
import com.wanying.entity.Book;
import com.wanying.entity.Cart;
import com.wanying.entity.Entry;
import com.wanying.service.StockService;

@Service
public class DefaultStockService implements StockService {

	@Autowired
	private BookDao bookDao;
	
	@Override
	public boolean updateStock(Cart cart) {
		if(haveStock(cart)) {
			for(Entry entry:cart.getEntries()) {
				int quantityToAdd = entry.getQuantity();
				Book book = entry.getBook();
				bookDao.updateStock(book, quantityToAdd);
			}
			return true;
		}else {
			return false;
		}
	}
	
	private boolean haveStock(Cart cart) {
		for(Entry entry:cart.getEntries()) {
			int quantityToAdd = entry.getQuantity();
			Book book = entry.getBook();
			int stockLevel = book.getStock();
			if(stockLevel<quantityToAdd) {
				return false;
			}
		}
		return true;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	
}

package com.wanying.strategy;

import java.util.List;

import com.wanying.entity.Book;

public interface SearchBookStrategy {
	List<Book> searchBook(String query);
}

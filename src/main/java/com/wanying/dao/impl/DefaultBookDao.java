package com.wanying.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.wanying.dao.BookDao;
import com.wanying.entity.Book;
import com.wanying.entity.Comment;

@Transactional
@Repository
public class DefaultBookDao implements BookDao {

	final String GET_ALL = "SELECT b FROM Book as b ORDER BY b.id";	
	final String SEARCH_TOPIC = "SELECT b FROM Book as b WHERE topic = :searchKeyword";	
	final String SEARCH_TITLE = "SELECT b FROM Book as b WHERE title LIKE :searchKeyword";	
	final String SEARCH_AUTHOR = "SELECT b FROM Book as b WHERE author LIKE :searchKeyword";	

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Book getBookById(int id) {
		return entityManager.find(Book.class, id);
	}

	@Override
	public void createBook(Book book) {
		entityManager.persist(book);
	}

	@Override
	public void updateStock(Book book,int stock) {
		int originalStock = book.getStock();
		int updateStock = originalStock-stock;
		book.setStock(updateStock);
		entityManager.flush();
	}

	@Override
	public List<Book> getAllBook() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> rootEntry = cq.from(Book.class);
        CriteriaQuery<Book> all = cq.select(rootEntry);
        TypedQuery<Book> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
	}

	@Override
	public List<Book> searchBookOnTopic(String searchKeyword) {
		TypedQuery<Book> query = entityManager.createQuery(SEARCH_TOPIC,Book.class);
	    query.setParameter("searchKeyword", searchKeyword);
	    return query.getResultList();
	}

	@Override
	public List<Book> searchBookOnAuthor(String searchKeyword) {
		TypedQuery<Book> query = entityManager.createQuery(SEARCH_AUTHOR,Book.class);
	    query.setParameter("searchKeyword", "%"+searchKeyword+"%");
	    return query.getResultList();
	}

	@Override
	public List<Book> searchBookOnTitle(String searchKeyword) {
		TypedQuery<Book> query = entityManager.createQuery(SEARCH_TITLE,Book.class);
	    query.setParameter("searchKeyword", "%"+searchKeyword+"%");
	    return query.getResultList();
	}

	@Override
	public Comment createComment(Book book,int rate, String username, String comment) {
		Comment newComment = new Comment();
		newComment.setComment(comment);
		newComment.setRate(rate);
		newComment.setUsername(username);
		newComment.setBook(book);
		entityManager.persist(newComment);
		return newComment;
	}

	@Override
	public void addComment(Book book, Comment comment) {
		if(CollectionUtils.isEmpty(book.getComment())) {
			Set<Comment> comments = new HashSet<>();
			comments.add(comment);
			book.setComment(comments);
		}else {
			book.getComment().add(comment);
		}
		entityManager.flush();
	}

	@Override
	public void updateComment(Comment c, int rate, String comment) {
		c.setId(rate);
		c.setComment(comment);
		entityManager.flush();
	}


	@Override
	public void updateStockForAdmin(Book book, int updatedStock) {
		book.setStock(updatedStock);
		entityManager.flush();
	}

}

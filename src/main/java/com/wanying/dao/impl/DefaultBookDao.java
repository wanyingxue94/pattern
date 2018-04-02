package com.wanying.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wanying.dao.BookDao;
import com.wanying.entity.Book;
import com.wanying.entity.User;

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
	public void createUser(Book book) {
		entityManager.persist(book);
	}

	@Override
	public void updateStock(int id,int stock) {
		Book book = getBookById(id);
		int originalStock = book.getStock();
		int updateStock = originalStock+stock>0?originalStock+stock:0;
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
		TypedQuery<Book> query = entityManager.createQuery(SEARCH_AUTHOR,Book.class);
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
		TypedQuery<Book> query = entityManager.createQuery(SEARCH_AUTHOR,Book.class);
	    query.setParameter("searchKeyword", "%"+searchKeyword+"%");
	    return query.getResultList();
	}

}

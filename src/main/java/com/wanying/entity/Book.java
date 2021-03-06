package com.wanying.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Book {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String author;
	private String topic;
	private String title;
	private int stock;
	private double price;
	@OneToMany
    @JoinTable(name="book_comment",
        joinColumns = @JoinColumn( name="book_id"),
        inverseJoinColumns = @JoinColumn( name="comment_id")
    )
	private Set<Comment> comment;
	@Lob
	private byte[] bookImage;
	
	public Book() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Set<Comment> getComment() {
		return comment;
	}
	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}
	public byte[] getBookImage() {
		return bookImage;
	}
	public void setBookImage(byte[] bookImage) {
		this.bookImage = bookImage;
	}
	
	private Book(BookBuilder builder) {
		this.author=builder.author;
		this.topic=builder.topic;
		this.title=builder.title;
		this.stock=builder.stock;
		this.price=builder.price;
		this.bookImage=builder.bookImage;
	}
	
	public static class BookBuilder{
		private String author;
		private String topic;
		private String title;
		private int stock;
		private double price;
		private byte[] bookImage;
		
		public BookBuilder setAuthor(String author) {
			this.author = author;
			return this;
		}
		public BookBuilder setTopic(String topic) {
			this.topic = topic;
			return this;
		}
		public BookBuilder setTitle(String title) {
			this.title = title;
			return this;
		}
		public BookBuilder setStock(int stock) {
			this.stock = stock;
			return this;
		}
		public BookBuilder setPrice(double price) {
			this.price = price;
			return this;
		}
		public BookBuilder setBookImage(byte[] bookImage) {
			this.bookImage = bookImage;
			return this;
		}
		public Book build() {
			return new Book(this);
		}
		
	}
	
}

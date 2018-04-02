package com.wanying.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Order {


	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	 @OneToMany
     @JoinTable(name="order_entry",
         joinColumns = @JoinColumn( name="order_id"),
         inverseJoinColumns = @JoinColumn( name="entry_id")
     )
	private Set<Entry> entries;
	private double totalPrice;
	@ManyToOne
	private User user;
	
	public Order() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Set<Entry> getEntries() {
		return entries;
	}

	public void setEntries(Set<Entry> entries) {
		this.entries = entries;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}

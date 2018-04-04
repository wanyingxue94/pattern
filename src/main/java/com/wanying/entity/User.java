package com.wanying.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	
	@Id
	private String id;
	private String password;
	@OneToOne
	private Cart cart;
	 @OneToMany
     @JoinTable(name="user_orders",
         joinColumns = @JoinColumn( name="user_id"),
         inverseJoinColumns = @JoinColumn( name="orders_id")
     )
	private Set<Orders> orders;
	private String paymentMethod;
	private String shippingAddress;
	
	public User() {
		
	}
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Cart getCart() {
		return cart;
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public Set<Orders> getOrders() {
		return orders;
	}
	
	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public String getShippingAddress() {
		return shippingAddress;
	}
	
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
}

package com.wanying.dto;

import java.util.List;

public class OrderDTO {

	private int id;
	private List<EntryDTO> entries;
	private UserDTO user;
	private double totalPrice;
	
	public OrderDTO() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<EntryDTO> getEntries() {
		return entries;
	}

	public void setEntries(List<EntryDTO> entries) {
		this.entries = entries;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}

package com.ordermatcher.model;

public class OrderItem {
	
	private String code;
	private int amount;
	private int price;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		return  (code + " " + this.amount + "@" + this.price);
	}
	
	@Override
	public int hashCode() {
		return this.price;
	}
		
	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		OrderItem orderItem = (OrderItem) obj;
		if ((orderItem.getAmount() == amount) && (orderItem.getPrice() == price)){
			flag = true;
		}
		return flag;
	}
	
	
}

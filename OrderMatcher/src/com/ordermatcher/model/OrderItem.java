package com.ordermatcher.model;

import com.ordermatcher.presentation.OrderMatcherConstants;
import com.ordermatcher.service.rules.IItemModel;

public class OrderItem implements IItemModel{
	
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		
		String returnValue = code;
		
		if (!OrderMatcherConstants.PRINT.equalsIgnoreCase(code)){
			returnValue = (code + "\t" + this.amount + "@" + this.price);
		}
		return  returnValue;
	}
	
	@Override
	public int hashCode() {
		return this.price;
	}
		
	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if (obj == null){
			return flag;
		}
		OrderItem orderItem = (OrderItem) obj;
		if ((orderItem.getAmount() == amount) && 
			(orderItem.getCode().equalsIgnoreCase(code)) && 
			(orderItem.getPrice() == price)){
			flag = true;
		}
		return flag;
	}
	
	
}

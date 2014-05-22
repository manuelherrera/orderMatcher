package com.ordermatcher.service.rules;


public interface IItemModel {
 
	public String getCode();
	public int getAmount();
	public int getPrice();
	
	public void setCode(String code);
	public void setAmount(int amount) ;
	public void setPrice(int price);
 
}

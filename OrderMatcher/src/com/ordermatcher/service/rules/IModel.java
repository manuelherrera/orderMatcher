package com.ordermatcher.service.rules;


public interface IModel {
 
	public String getCode();
	public int getAmount();
	public int getPrice();
	
	public void setCode();
	public void setAmount();
	public void setPrice();
 
}

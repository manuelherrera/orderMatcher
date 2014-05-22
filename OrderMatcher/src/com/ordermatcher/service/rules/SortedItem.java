package com.ordermatcher.service.rules;

public class SortedItem<T> implements IItem {

	private T item;	
	
	public SortedItem(T item) {
		this.item = item;
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("item: "+ item.toString() +"\n");
		return output.toString();
	}
	
	public T getItem(){
		return item;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAmount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPrice() {
		// TODO Auto-generated method stub
		
	}
	
}
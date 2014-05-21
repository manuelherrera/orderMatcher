package com.ordermatcher.service.rules;


public class SortedItem<T> implements Identificable {

	private T item;
	private String id;
	
	
	public SortedItem(T item, String id) {
		this.item = item;
		this.id = id;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("item: "+ item.toString() +"\t")
			  .append("id: " + id + "\n");
		return output.toString();
	}
	
	public T getItem(){
		return item;
	}
	
}

package com.ordermatcher.service.rules;


public class SortedItem {

	private IModel item;
	private String id;
	
	
	public SortedItem(IModel item, String id) {
		this.item = item;
		this.id = id;
	}

	public String getId() {
		return this.id;
	}
	
	public IModel getItem(){
		return item;
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("item: "+ item.toString() +"\t")
			  .append("id: " + id + "\n");
		return output.toString();
	}
	
}
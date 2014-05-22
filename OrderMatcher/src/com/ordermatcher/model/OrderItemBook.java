package com.ordermatcher.model;

import java.util.ArrayList;
import java.util.List;

public class OrderItemBook {
	private List<OrderItem> orderItemList = new ArrayList<OrderItem>();

	public void addOrderItem(OrderItem orderItem){
		orderItemList.add(orderItem);
	}
	
	public List<OrderItem> getOrderItemList(){
		return orderItemList;
	}
	
	
	@Override
	public String toString() {
	  StringBuilder output = new StringBuilder();
	  for(OrderItem item: orderItemList){
		  output.append(item.toString()).append("\n");
	  }
	  return output.toString();
	}
}

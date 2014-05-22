package com.ordermatcher.service;

import com.ordermatcher.model.OrderItem;
import com.ordermatcher.model.OrderItemBook;
import com.ordermatcher.service.rules.ITradingService;

public class OrderMatcherService {

	private static ITradingService tradingService =  new TradingService();
			
	private static OrderItemBook book = new OrderItemBook();
	
	public void addToOrderItemBook(OrderItem orderItem){
		//1. Add item into the order book to save all the transactions
		book.addOrderItem(orderItem);
		
		//2.  
		
		
	}
	
	
	
}

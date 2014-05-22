package com.ordermatcher.service;

import com.ordermatcher.model.OrderItem;
import com.ordermatcher.model.OrderItemBook;
import com.ordermatcher.service.rules.ITradingService;

public class OrderMatcherService {

	private static ITradingService tradingService =  new TradingService();
			
	private static OrderItemBook book = new OrderItemBook();
	
	/**
	 * 
	 * @param orderItem
	 */
	public void addToOrderItemBook(OrderItem orderItem){
		
		//1. Add item into the order book to save all the transactions
		book.addOrderItem(orderItem);
		
	}
	
	/**
	 * 
	 * @param orderItem
	 * @return
	 */
	public String checkForMatchesAndTrade(OrderItem orderItem){
		
		//1.  Find for matches and trade if possible
		String output = tradingService.findMatches(orderItem, book.getOrderItemList().size());
		
		return output;
	}
	
}

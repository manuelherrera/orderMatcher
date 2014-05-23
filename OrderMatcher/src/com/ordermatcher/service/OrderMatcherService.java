package com.ordermatcher.service;

import com.ordermatcher.model.OrderItem;
import com.ordermatcher.model.OrderItemBook;
import com.ordermatcher.presentation.OrderMatcherConstants;
import com.ordermatcher.service.rules.ITradingService;

public class OrderMatcherService {

	private static ITradingService tradingService =  new TradingService();
			
	private static OrderItemBook book = new OrderItemBook();
	
	public OrderMatcherService(){
		
	}
	
	public OrderMatcherService(boolean init){
		if (init){
			tradingService =  new TradingService(true);
			book = new OrderItemBook();
		}
	}
	
	/**
	 * 
	 * @param orderItem
	 * @return
	 */
	public String computeCommand(OrderItem orderItem){
		String output = null;
		
		if (orderItem.getCode().equals(OrderMatcherConstants.PRINT)){
			
			output = tradingService.printBook();
			
		}else{
		
			addToOrderItemBook(orderItem);
			
			output = checkForMatchesAndTrade(orderItem);
		}

		
		return (output == null  ?  "" : output);
		
	}
	
	
	/**
	 * 
	 * @param orderItem
	 */
	private void addToOrderItemBook(OrderItem orderItem){
		
		//1. Add item into the order book to save all the transactions
		book.addOrderItem(orderItem);
		
	}
	
	/**
	 * 
	 * @param orderItem
	 * @return
	 */
	private String checkForMatchesAndTrade(OrderItem orderItem){
		
		//1.  Find for matches and trade if possible
		String output = tradingService.findMatches(orderItem, book.getOrderItemList().size());
		
		return output;
	}
	
}

package com.ordermatcher.service;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.ordermatcher.presentation.OrderMatcherConstants;
import com.ordermatcher.service.rules.ITradingRulesEngine;
import com.ordermatcher.service.rules.SortedBook;
import com.ordermatcher.service.rules.SortedItem;

public class TradingRulesEngine implements ITradingRulesEngine {

	private SortedBook book;
	
	/**
	 * 
	 * @param book
	 */
	public TradingRulesEngine(SortedBook book) {
		this.book = book;
	}

	@Override
	public String calculateTrading(SortedItem orderItem) {
		
		String returnValue = null;
		
		SortedSet<SortedItem>  sortedItemSetShadow = null;
		// Obtain code
		String code = orderItem.getItem().getCode();
		
		//Check for SELL OR BUY
		if (code.endsWith(OrderMatcherConstants.BUY)){
			sortedItemSetShadow = new TreeSet<SortedItem>(book.getSellSet()); 
		}else if(code.endsWith(OrderMatcherConstants.SELL)){
			sortedItemSetShadow = new TreeSet<SortedItem>(book.getBuySet());
		}else{
			return null; // Save check and not accept other codes besides BUY and SELL
		}
				
		Iterator<SortedItem> iterator = sortedItemSetShadow.iterator();
		
		float price = orderItem.getItem().getPrice();
		int amount = orderItem.getItem().getAmount();
				
		int amountCheck = 1;
		
		StringBuilder output = new StringBuilder();
		
		do{		
			
			SortedItem item = iterator.next();
			int tradePrice = item.getItem().getPrice(); 
			int tradeAmount = item.getItem().getAmount();
			
			if (code.endsWith(OrderMatcherConstants.BUY)){
				amountCheck = checkForASellMatch(item, price, amount);	
			}else if(code.endsWith(OrderMatcherConstants.SELL)){
				amountCheck = checkForABuyMatch(item, price, amount);
			}
			
			if (amountCheck >= 0){
				output.append(OrderMatcherConstants.TRADE).append( " ").
					   append(tradeAmount + amountCheck).append("@").
					   append(tradePrice);
				iterator.remove();
			}
			//Pending 
			amount = amountCheck;
			
		}while (amountCheck == Integer.MIN_VALUE || amount == 0 );

		if (amountCheck == Integer.MIN_VALUE){
			if (code.endsWith(OrderMatcherConstants.BUY)){
				book.getBuySet().add(orderItem);	
			}else if(code.endsWith(OrderMatcherConstants.SELL)){
				book.getSellSet().add(orderItem);
			}	
			//returnValue = null;
			output.setLength(0);
		}else if(amount == 0){
			returnValue = output.toString();
		}

		return returnValue;
	}
	

	/**
	 * 
	 * @param item
	 * @param price
	 * @param amount
	 * @return
	 */
	private int checkForASellMatch(SortedItem item, float price, int amount){
		
		//1. Look if the price is  higher or equal to the sorted element 
		if (item.getItem().getPrice() <= price){
			item.getItem().setAmount(item.getItem().getAmount() - amount);			
			if (item.getItem().getAmount() < 0){
				item.getItem().setAmount(0);
			}
			
			return item.getItem().getAmount();
		}
		
		return Integer.MIN_VALUE;
	}
	
	/**
	 * 
	 * @param item
	 * @param price
	 * @param amount
	 * @return
	 */
	private int checkForABuyMatch(SortedItem item, float price, int amount){
		
		//1. Look if the price is  higher or equal to the sorted element 
		if (item.getItem().getPrice() > price){
			item.getItem().setAmount(item.getItem().getAmount() - amount);			
			if (item.getItem().getAmount() < 0){
				item.getItem().setAmount(0);
			}
			
			return item.getItem().getAmount();
		}
		
		return Integer.MIN_VALUE;
	}	
}

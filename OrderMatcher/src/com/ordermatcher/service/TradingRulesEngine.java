package com.ordermatcher.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

import com.ordermatcher.presentation.OrderMatcherConstants;
import com.ordermatcher.service.rules.ITradingRulesEngine;
import com.ordermatcher.service.rules.ItemModelComparator;
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
		Collection<SortedItem> removeItemList = new LinkedList<SortedItem>();
		
		// Obtain code
		String code = orderItem.getItem().getCode();
		
		//Check for SELL OR BUY
		if (code.equals(OrderMatcherConstants.BUY)){
			sortedItemSetShadow = new TreeSet<SortedItem>(new ItemModelComparator()); 
			sortedItemSetShadow.addAll(book.getSellSet());
		}else if(code.equals(OrderMatcherConstants.SELL)){
			sortedItemSetShadow = new TreeSet<SortedItem>(new ItemModelComparator());
			sortedItemSetShadow.addAll(book.getBuySet());
		}else{
			return null; // Save check and not accept other codes besides BUY and SELL
		}
		if (sortedItemSetShadow.size() == 0){
			return null;
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
			
			if (code.equals(OrderMatcherConstants.BUY)){
				amountCheck = checkForASellMatch(item, price, amount);	
			}else if(code.equals(OrderMatcherConstants.SELL)){
				amountCheck = checkForABuyMatch(item, price, amount);
			}
			
			if (amountCheck > 0){
				output.append(OrderMatcherConstants.TRADE).append( "\t").
					   append(tradeAmount - amountCheck).append("@").
					   append(tradePrice).append("\n");
				
			}else if(amountCheck == 0){
				removeItemList.add(item);
			}
			//Pending 
			amount = amount - tradeAmount;
			
		}while (amountCheck != Integer.MIN_VALUE && amount >= 0 );

		iterator = null;
		
		//Remove sorted items
		sortedItemSetShadow.removeAll(removeItemList);
		if (amountCheck == Integer.MIN_VALUE){
			if (code.equals(OrderMatcherConstants.BUY)){
				book.getBuySet().add(orderItem);	
			}else if(code.equals(OrderMatcherConstants.SELL)){
				book.getSellSet().add(orderItem);
			}	
			//returnValue = null;
			output.setLength(0);
		}else if(amount < 0){
			
			if (code.equals(OrderMatcherConstants.BUY)){
				book.setSellSet(sortedItemSetShadow);	
			}else if(code.equals(OrderMatcherConstants.SELL)){
				book.setBuySet(sortedItemSetShadow);
			}
			
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

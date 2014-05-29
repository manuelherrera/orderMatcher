package com.ordermatcher.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedSet;

import com.ordermatcher.presentation.OrderMatcherConstants;
import com.ordermatcher.service.rules.ITradingRulesEngine;
import com.ordermatcher.service.rules.SortedBook;
import com.ordermatcher.service.rules.SortedItem;
/**
 * 
 * @author Jose
 *
 */
public class TradingRulesEngine implements ITradingRulesEngine {

	private SortedBook book;
	
	/**
	 * 
	 * @param book
	 */
	public TradingRulesEngine(SortedBook book) {
		this.book = book;
	}

	/**
	 * 
	 */
	@Override
	public String calculateTrading(SortedItem orderItem) {
		
		String returnValue = null;

		// Obtain code
		String code = orderItem.getItem().getCode();		
		
		SortedSet<SortedItem>  sortedItemSetShadow = null;
		Collection<SortedItem> removeItemList = new LinkedList<SortedItem>();
	
		// return null in the case we dont need to analyze anything
		if ((book.getBuySet().size() == 0 && OrderMatcherConstants.SELL.endsWith(code)) ||
			(book.getSellSet().size() == 0 && OrderMatcherConstants.BUY.endsWith(code))){
			return null;
		}else if (!code.equals(OrderMatcherConstants.BUY) && code.equals(OrderMatcherConstants.SELL)){
			// Save check and not accept other codes besides BUY and SELL
			return null;
		}
		
		//Declare Clone Sets
		SortedSet<SortedItem> sellSet = null, buySet = null ; 
		
		//Check for SELL OR BUY and select the correct Set structure
		if (code.equals(OrderMatcherConstants.BUY)){
			sellSet = book.getSellSetClone(); 
			sortedItemSetShadow = sellSet; 
		
		}else if(code.equals(OrderMatcherConstants.SELL)){
			buySet = book.getBuySetClone();
			sortedItemSetShadow = buySet;

		}
		
		// Set the iterator
		Iterator<SortedItem> iterator = sortedItemSetShadow.iterator();
		
		// Set price and amounts to start doing calculations
		float price = orderItem.getItem().getPrice();
		int amount = orderItem.getItem().getAmount();
				
		int amountCheck = 1;
		
		//String buffer for Trading if matches exists
		StringBuilder output = new StringBuilder();
		
		boolean flag = true;
		
		do{		
			
			if (iterator.hasNext()){
				SortedItem item = iterator.next();
				//All operations will be done by a clone to provide rollbacks as needed.
				SortedItem itemClone = item.clone();
				
				int tradePrice = item.getItem().getPrice(); 
				int tradeAmount = item.getItem().getAmount();
				
				if (code.equals(OrderMatcherConstants.BUY)){
					amountCheck = checkForASellMatch(item, price, amount);	
				}else if(code.equals(OrderMatcherConstants.SELL)){
					amountCheck = checkForABuyMatch(item, price, amount);
				}
				
				//Case when the sell order amount is higher from the buy order amount
				if (amountCheck > 0){
					output.append( "  ").append(OrderMatcherConstants.TRADE).append( "\t").
						   append(tradeAmount - amountCheck).append("@").
						   append(tradePrice).append("\n");
					//Pending 
					amount = amount - tradeAmount;
					
				// Case when we need to remove the 0 from the order book	
				}else if(amountCheck == 0){
					output.append( "  ").append(OrderMatcherConstants.TRADE).append( "\t").
					   append(tradeAmount - amountCheck).append("@").
					   append(tradePrice).append("\n");
					removeItemList.add(itemClone);
					iterator.remove();
					//Pending 
					amount = amount - tradeAmount;
				}
			}else{
				if (amount >= 0){
					flag = false;
				}else{
					amountCheck = Integer.MIN_VALUE;
				}
			}
			
		}while (amountCheck != Integer.MIN_VALUE && amount > 0 && flag);
		// close the iterator
		iterator = null;
		//Case when we need to add the order to the order book
		if (amountCheck == Integer.MIN_VALUE){
			if (code.equals(OrderMatcherConstants.BUY)){
				book.getBuySet().add(orderItem);	
			}else if(code.equals(OrderMatcherConstants.SELL)){
				book.getSellSet().add(orderItem);
			}	
			//returnValue = null;
			output.setLength(0);
		}else if(amount <= 0  || flag){
			// Case when we need to update the order book with the correct calculations
			if (code.equals(OrderMatcherConstants.BUY)){
				sellSet.removeAll(removeItemList);
				book.setSellSet(sellSet);	
			}else if(code.equals(OrderMatcherConstants.SELL)){
				buySet.removeAll(removeItemList);
				book.setBuySet(buySet);
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

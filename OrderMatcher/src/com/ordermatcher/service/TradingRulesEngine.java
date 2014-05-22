package com.ordermatcher.service;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.ordermatcher.service.rules.IItemModel;
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
	public String calculateTrading(IItemModel orderItem) {
		SortedSet<SortedItem>  sortedItemSetShadow = new TreeSet<SortedItem>(book.getSortedItemSet()); 
		
		Iterator<SortedItem> iterator = sortedItemSetShadow.iterator();
		
		float price = orderItem.getPrice();
		int amount = orderItem.getAmount();
				
		int amountCheck = 1;
		int index = -1;
		
		do{		
			SortedItem item = iterator.next();
			if (item != null){

				amountCheck = checkForAMatch(item, price, amount) ;				
				iterator.remove();
				item = iterator.next();
				index++;//Index number of the sorted book
				
			}else{
				amountCheck = 0;
			}
			
		}while (amountCheck == Integer.MIN_VALUE || amountCheck == 0 );

		if (index > -1){
			System.out.println(sortedItemSetShadow.size());
		}
		
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	private int checkForAMatch(SortedItem item, float price, int amount){
		
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
	
}

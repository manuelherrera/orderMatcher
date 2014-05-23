package com.ordermatcher.service;

import com.ordermatcher.presentation.OrderMatcherConstants;
import com.ordermatcher.service.rules.IItemModel;
import com.ordermatcher.service.rules.ITradingRulesEngine;
import com.ordermatcher.service.rules.ITradingService;
import com.ordermatcher.service.rules.ItemModelComparator;
import com.ordermatcher.service.rules.SortedBook;
import com.ordermatcher.service.rules.SortedItem;

public class TradingService implements ITradingService {

	private static SortedBook sortedBook = null; 
	
	private static ITradingRulesEngine rulesEngine = null;
	
	static{
		sortedBook = new SortedBook(new ItemModelComparator());
		rulesEngine = new TradingRulesEngine(sortedBook);
	}
	
	public TradingService(){
		
	}
	
	public TradingService(boolean init){
		
		if(init){
			sortedBook = new SortedBook(new ItemModelComparator());
			rulesEngine = new TradingRulesEngine(sortedBook);
		}
	}	
	
	@Override
	public String findMatches(IItemModel item, int index) {
		
		//1. Convert to SortedItem
		SortedItem sortedItem = buildSortedItem(item, index);
		
		//2. check for trading 
		String output = rulesEngine.calculateTrading(sortedItem);		
		
		//3 Add to Sorted Book
		if (output == null ){
			addSortedItemToBook(sortedItem);
		}
		
		

		
		return output;
	}

	@Override
	public String printBook() {
		// TODO Auto-generated method stub
		return sortedBook.toString();
	}
	/**
	 * 
	 * @param item
	 * @param index
	 * @return
	 */
	public SortedItem buildSortedItem(IItemModel item, int index) {
		if (item == null)
			return null;
		String id = new String(index+String.valueOf(item.getPrice()));
		SortedItem sortedItem = new SortedItem(item, id);
		return sortedItem;
	}

	/**
	 * 
	 * @param orderItem
	 */
	private void addSortedItemToBook(SortedItem orderItem) {
		if (orderItem == null)
			return;
		
		if (OrderMatcherConstants.BUY.equals(orderItem.getItem().getCode())){
			sortedBook.addItemToBuySet(orderItem);
		}
		if (OrderMatcherConstants.SELL.equals(orderItem.getItem().getCode())){
			sortedBook.addItemToSellSet(orderItem);
		}		
		
	}

}

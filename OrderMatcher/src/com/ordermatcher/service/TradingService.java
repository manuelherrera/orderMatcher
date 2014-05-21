package com.ordermatcher.service;

import com.ordermatcher.model.OrderItem;
import com.ordermatcher.presentation.OrderMatcherConstants;
import com.ordermatcher.service.rules.ITradingService;
import com.ordermatcher.service.rules.ItemComparator;
import com.ordermatcher.service.rules.SortedBook;
import com.ordermatcher.service.rules.SortedItem;

public class TradingService implements ITradingService<OrderItem> {

	private static SortedBook<OrderItem> sortedBook = new SortedBook<OrderItem>(new ItemComparator<OrderItem>());
	
	@Override
	public boolean checkForTrading(OrderItem orderItem) {
		
		//1. Build Sorted Item
		SortedItem<OrderItem> sortedItem = buildSortedItem(orderItem, sortedBook.getSortedItemSet().size());
		
		//2. Add Sorted Item to Sorted book
		addSortedItemToBook(sortedItem);
		
		//3. check for trading 
		if (OrderMatcherConstants.BUY.equals(orderItem.getCode())){
			return true;
		}
		return false;
	}

	@Override
	public void doTrade() {
		
	}
	
	private SortedItem<OrderItem> buildSortedItem(OrderItem item, int index) {
		if (item == null)
			return null;
		String id = new String(index+String.valueOf(item.getPrice()));
		SortedItem<OrderItem> sortedItem = new SortedItem<OrderItem>(item, id);
		return sortedItem;
	}

	private void addSortedItemToBook(SortedItem<OrderItem> item) {
		if (item == null)
			return;
		sortedBook.addSortedItem(item);
	}
	
}

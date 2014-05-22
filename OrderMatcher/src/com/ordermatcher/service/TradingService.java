package com.ordermatcher.service;

import com.ordermatcher.service.rules.IModel;
import com.ordermatcher.service.rules.ITradingService;
import com.ordermatcher.service.rules.ItemModelComparator;
import com.ordermatcher.service.rules.SortedBook;
import com.ordermatcher.service.rules.SortedItem;

public class TradingService implements ITradingService<SortedItem> {

	private static SortedBook sortedBook = new SortedBook(new ItemModelComparator());
	
	@Override
	public String findMatches(SortedItem item) {
		
		//1. Add Sorted Item to Sorted book
		//addSortedItemToBook(item.getItem());
		
		//3. check for trading 
		return null;
	}

	
	public SortedItem buildSortedItem(IModel item, int index) {
		if (item == null)
			return null;
		String id = new String(index+String.valueOf(item.getPrice()));
		SortedItem sortedItem = new SortedItem(item, id);
		return sortedItem;
	}

	private void addSortedItemToBook(SortedItem orderItem) {
		if (orderItem == null)
			return;
		sortedBook.addSortedItem(orderItem);
	}

}

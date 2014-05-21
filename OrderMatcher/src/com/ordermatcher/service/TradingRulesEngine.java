package com.ordermatcher.service;

import java.util.Iterator;

import com.ordermatcher.model.OrderItem;
import com.ordermatcher.service.rules.ITradingRulesEngine;
import com.ordermatcher.service.rules.SortedBook;
import com.ordermatcher.service.rules.SortedItem;

public class TradingRulesEngine implements ITradingRulesEngine<OrderItem> {

	private SortedBook<OrderItem> book;
	
	public TradingRulesEngine(SortedBook<OrderItem> book) {
		this.book = book;
	}

	@Override
	public String calculateTrading(OrderItem orderItem) {
		Iterator<SortedItem<OrderItem>> iterator = book.getSortedItemSet().iterator();
		while (iterator.hasNext()){
			SortedItem<OrderItem> item = iterator.next();
			item.getItem();
				
		}
		
		return null;
	}
	
	
}

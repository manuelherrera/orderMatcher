package com.ordermatcher.service.rules;


public interface ITradingService {
	
	public String findMatches (IItemModel item, int index); // Retrieve stack of trades
	
	public String printBook();
	
}

package com.ordermatcher.service.rules;


public interface ITradingService<T> {
	
	public String findMatches (T item); // Retrieve stack of trades
	
}

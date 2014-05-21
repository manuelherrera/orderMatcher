package com.ordermatcher.service.rules;

public interface ITradingRulesEngine<T> {

	public String calculateTrading(T item);
	
}

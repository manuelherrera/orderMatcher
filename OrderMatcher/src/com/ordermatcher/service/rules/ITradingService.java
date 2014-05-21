package com.ordermatcher.service.rules;

import com.ordermatcher.model.OrderItem;

public interface ITradingService<T> {

	public boolean checkForTrading(OrderItem orderItem);
	public void doTrade();
	
}

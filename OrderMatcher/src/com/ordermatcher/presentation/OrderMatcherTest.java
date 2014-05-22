/**
 * 
 */
package com.ordermatcher.presentation;

import com.ordermatcher.model.OrderItem;
import com.ordermatcher.service.OrderMatcherService;
import com.ordermatcher.service.TradingService;

/**
 * @author Jose
 *
 */
public class OrderMatcherTest {

	/**
	 * 
	 */
	public OrderMatcherTest() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main (String args[]){
		
		OrderMatcherService service = new OrderMatcherService();
		
		OrderItem item = new OrderItem();
		
		item = buildOrderItem(OrderMatcherConstants.SELL, 100, 10);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.SELL, 100, 15);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.BUY, 120, 17);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.PRINT, 0, 0);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
	}

	public static OrderItem buildOrderItem(String code, int amount, int price ){
		OrderItem item = new OrderItem();
		item.setAmount(amount);
		item.setCode(code);
		item.setPrice(price);
		
		return item;
	}
}

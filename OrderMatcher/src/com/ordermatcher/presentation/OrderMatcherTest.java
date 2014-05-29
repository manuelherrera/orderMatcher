/**
 * 
 */
package com.ordermatcher.presentation;

import java.util.Date;

import com.ordermatcher.model.OrderItem;
import com.ordermatcher.service.OrderMatcherService;

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
		
		testcase1();
		testcase2();
		testcase3();
		testcase4();
		testcase5();
		
	}

	public static OrderItem buildOrderItem(String code, int amount, int price ){
		OrderItem item = new OrderItem();
		item.setAmount(amount);
		item.setCode(code);
		item.setPrice(price);
		
		return item;
	}
	
	public static void testcase1(){
		OrderMatcherService service = new OrderMatcherService(true);
		long time = System.currentTimeMillis();
		
		System.out.println("\n------------------TESTCASE 1-------------");
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
		
		showStatistics(time);
		System.out.println("------------------END TESTCASE 1-------------");			
	}
	
	public static void testcase2(){
		OrderMatcherService service = new OrderMatcherService(true);

		long time = System.currentTimeMillis();
		
		System.out.println("\n------------------TESTCASE 2-------------");		
		OrderItem item = new OrderItem();
		
		item = buildOrderItem(OrderMatcherConstants.SELL, 50, 10);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.SELL, 50, 12);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.BUY, 120, 17);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.PRINT, 0, 0);
		System.out.println(item);
		System.out.print(service.computeCommand(item));		
		
		showStatistics(time);	
		System.out.println("------------------END TESTCASE 2-------------");			
	}
	
	public static void testcase3(){
		OrderMatcherService service = new OrderMatcherService(true);
		
		long time = System.currentTimeMillis();
	
		System.out.println("\n------------------TESTCASE 3-------------");		
		OrderItem item = new OrderItem();
		
		item = buildOrderItem(OrderMatcherConstants.SELL, 100, 10);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.SELL, 200, 15);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.BUY, 150, 12);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.PRINT, 0, 0);
		System.out.println(item);
		System.out.print(service.computeCommand(item));		
		
		showStatistics(time);		
		System.out.println("------------------END TESTCASE 3-------------");			
	}	
	
	public static void testcase4(){
		OrderMatcherService service = new OrderMatcherService(true);
		
		long time = System.currentTimeMillis();
	
		System.out.println("\n------------------TESTCASE 4-------------");		
		OrderItem item = new OrderItem();
		
		item = buildOrderItem(OrderMatcherConstants.SELL, 100, 10);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.SELL, 200, 15);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.BUY, 150, 8);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.PRINT, 0, 0);
		System.out.println(item);
		System.out.print(service.computeCommand(item));		
		
		showStatistics(time);	
		System.out.println("------------------END TESTCASE 4-------------");			
	}
	
	public static void testcase5(){
		OrderMatcherService service = new OrderMatcherService(true);
		
		long time = System.currentTimeMillis();
		
		System.out.println("\n------------------TESTCASE 5-------------");		
		OrderItem item = new OrderItem();
		
		item = buildOrderItem(OrderMatcherConstants.SELL, 100, 5);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.SELL, 90, 10);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.SELL, 120, 10);
		System.out.println(item);
		System.out.print(service.computeCommand(item));		
		
		item = buildOrderItem(OrderMatcherConstants.BUY, 130, 10);
		System.out.println(item);
		System.out.print(service.computeCommand(item));
		
		item = buildOrderItem(OrderMatcherConstants.PRINT, 0, 0);
		System.out.println(item);
		System.out.print(service.computeCommand(item));	
		
		showStatistics(time);	
		System.out.println("------------------END TESTCASE 5-------------");			
	}		
	
	/**
	 * 
	 */
	public static void showStatistics(long time){
		System.out.println("\n------------------------------- ");	
		System.out.println("Statistics ");
		System.out.println("------------------------------- ");	
		System.out.println("Begin time : " + new Date(time) );		
		time = System.currentTimeMillis() - time;
		System.out.println("End time : " + new Date(System.currentTimeMillis()));	
		System.out.println("Processing time : " + time);	
	}
}

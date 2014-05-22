package com.ordermatcher.presentation;

import java.util.Scanner;

import com.ordermatcher.model.OrderItem;
import com.ordermatcher.service.OrderMatcherService;

public class OrderMatcher {

	OrderMatcherService orderMatcherService = null;
	
	private OrderItem gettingInputValues() throws Exception{
		OrderItem item = null;
		String code ="";
		int volume = 0;
		int price = 0;
		
		Scanner inputCode = new Scanner(System.in);
		System.out.println("Please enter a data (BUY/SELL/PRINT) (amount)(@)(price), SELL 100@10\t");
		code = inputCode.nextLine().toUpperCase();
		if (!code.equals("PRINT")){
			try{	
				String[] result = code.split("\\s");
				code = result[0];
				String aux = result[1];
				result = aux.split("@");
				volume = Integer.parseInt( result[0]);
				price = Integer.parseInt (result[1]);
			
				item = new OrderItem();
				item.setCode(code);
				item.setAmount(volume);
				item.setPrice(price);
				
			}catch(Exception e){
				throw new Exception("Error: " + "The format is not correct, please try again"); 
			}
		}else{
			item = new OrderItem();
			item.setCode(code);
			item.setAmount(0);
			item.setPrice(0);
		}	
		return item;
	}	
		
	private void doTrading(OrderItem item){
		
		if (!item.equals(null)){
			orderMatcherService = new OrderMatcherService();
			System.out.print(orderMatcherService.computeCommand(item));		
		}
		
	}
	
	public static void main(String[] args){
		OrderMatcher orderMatcher = new OrderMatcher();
		String answer = "";
		Scanner sc = null;
		do {			
			try {
				orderMatcher.doTrading(orderMatcher.gettingInputValues());
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("\n");
			sc = new Scanner (System.in);
			System.out.println("Do you want to exit? (y/yes)\n");
			answer = sc.nextLine().toLowerCase();
		}
		while(!answer.contentEquals("y") || !answer.contentEquals("yes"));
	}
}
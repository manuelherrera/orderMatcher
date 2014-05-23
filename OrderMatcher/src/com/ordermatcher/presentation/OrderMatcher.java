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
		System.out.print("\nPlease enter a data (BUY/SELL/PRINT) (amount)(@)(price), 'SELL 100@10':\n ");
		code = inputCode.nextLine().toUpperCase();
		if (!code.equals("PRINT")){
			try{	
				String[] result = code.split("\\s|@");
				code = result[0];
				volume = Integer.parseInt( result[1]);
				price = Integer.parseInt (result[2]);
			
				item = new OrderItem();
				item.setCode(code);
				item.setAmount(volume);
				item.setPrice(price);
				
			}catch(Exception e){
				System.out.println("Error: " + "The format is not correct, please try again"); 
			}
		}else
			if (code.equals("PRINT")){
				item = new OrderItem();
				item.setCode(code);
				item.setAmount(0);
				item.setPrice(0);
			}
			
		return item;
	}	
		
	private void findMatch(OrderItem item){
		
		if (!item.equals(null)){
			orderMatcherService = new OrderMatcherService();
			System.out.print(orderMatcherService.computeCommand(item));		
		}
	}
	
	public static void main(String[] args){
		
		OrderMatcher orderMatcher = new OrderMatcher();
		String answer = "";
		Scanner sc = null;
		do{			
			try {
				orderMatcher.findMatch(orderMatcher.gettingInputValues());
			}catch (Exception e) {
				System.out.println("Error: " + "The format is not correct, format valid 'SELL 100@10'"); 
			}
			System.out.println("\n");
			sc = new Scanner (System.in);
			System.out.println("Do you want to exit?, (yes):\n ");
			answer = sc.nextLine().toLowerCase();
		}
		while (!answer.contentEquals("yes"));
		if (answer.contentEquals("yes")){
			System.out.println("See you later...!!!, next time");
		}
	}
}
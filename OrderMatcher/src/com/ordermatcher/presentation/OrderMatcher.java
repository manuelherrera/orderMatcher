package com.ordermatcher.presentation;

import java.util.Scanner;

import com.ordermatcher.model.OrderItem;
import com.ordermatcher.service.OrderMatcherService;

public class OrderMatcher {

	OrderMatcherService orderMatcherService = null;
	
	
	public void gettingInputValues(){
		String code ="";
		int volume = 0;
		int price = 0;
		
		Scanner inputCode = new Scanner(System.in);
		System.out.println("Please enter a data (BUY/SELL/PRINT) \t");
		code = inputCode.nextLine();
		code = code.toUpperCase();
		if (!code.contentEquals("PRINT")){
			Scanner inputVolume = new Scanner(System.in);
			System.out.println("Please enter a amount \t");
			volume = inputVolume.nextInt();

			Scanner inputPrice = new Scanner(System.in);
			System.out.println("Please enter a price \t");
			price = inputPrice.nextInt();
		}else
			if (code.contentEquals("PRINT")){
				volume = 0;
				price = 0;
			}
		
		OrderItem item = new OrderItem();
		item.setCode(code);
		item.setAmount(volume);
		item.setPrice(price);
		
		if (!item.equals(null)){
			orderMatcherService = new OrderMatcherService();
			System.out.print(orderMatcherService.computeCommand(item));		}
		
	}
	
	public static void main(String[] args){
		OrderMatcher orderMatcher = new OrderMatcher();
		String answer = "";

		do {			
			orderMatcher.gettingInputValues();
			System.out.println("\n");
			Scanner sc = new Scanner (System.in);
			System.out.println("Do you want to exit? (exit)\n");
			answer = sc.nextLine();
			answer = answer.toLowerCase();
		}
		while(!answer.contentEquals("exit"));
	}
}

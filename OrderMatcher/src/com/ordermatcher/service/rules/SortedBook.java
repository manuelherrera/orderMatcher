package com.ordermatcher.service.rules;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedBook{

	private SortedSet<SortedItem> sellSet;
	private SortedSet<SortedItem> buySet;
	
	/**
	 * 
	 * @param itemComparator
	 */
	public SortedBook(ItemModelComparator itemComparator) {
		sellSet = new TreeSet<SortedItem>(itemComparator);
		buySet = new TreeSet<SortedItem>(itemComparator);
	}

	/**
	 * 
	 * @param item
	 */
	public void addItemToSellSet(SortedItem item){
		if (item == null)
			return;
		
		sellSet.add(item);
	}
	
	/**
	 * 
	 * @param item
	 */
	public void addItemToBuySet(SortedItem item){
		if (item == null)
			return;
		
		buySet.add(item);		
	}
	
	public SortedSet<SortedItem> getSellSet() {
		return sellSet;
	}


	public void setSellSet(SortedSet<SortedItem> sellSet) {
		this.sellSet = sellSet;
	}


	public SortedSet<SortedItem> getBuySet() {
		return buySet;
	}


	public void setBuySet(SortedSet<SortedItem> buySet) {
		this.buySet = buySet;
	}


	@Override
	public String toString() {
		//Sell stack
		Iterator<SortedItem> iterator = sellSet.iterator();
		StringBuilder output = new StringBuilder();
		output.append("--------------SELL---------------\n");
		while (iterator.hasNext()){
			output.append(iterator.next().toString()).append("\n");
		}
		
		output.append("--------------BUY---------------\n");
		
		//Buy Stack
		iterator = buySet.iterator();

		while (iterator.hasNext()){
			output.append(iterator.next().toString()).append("\n");
		}
		return output.toString();
	}
}

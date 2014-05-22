package com.ordermatcher.service.rules;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedBook<T>{

	private SortedSet<SortedItem<T>> sortedItemBuySet;
	private SortedSet<SortedItem<T>> sortedItemSellSet;
	
	public SortedBook(ItemComparator<T> itemComparator) {
		sortedItemBuySet = new TreeSet<SortedItem<T>>(itemComparator);
	}

	public SortedSet<SortedItem<T>> getSortedItemSet() {
		return sortedItemBuySet;
	}

	public void addSortedItem (SortedItem<T> sortedItem){
		sortedItemBuySet.add(sortedItem);
	}
		
	@Override
	public String toString() {
		Iterator<SortedItem<T>> iterator = sortedItemBuySet.iterator();
		StringBuilder output = new StringBuilder();
		while (iterator.hasNext()){
			output.append(iterator.next().toString()).append("\n");
		}
		return output.toString();
	}
}

package com.ordermatcher.service.rules;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedBook<T>{

	private SortedSet<SortedItem<T>> sortedItemSet;
	
	public SortedBook(ItemComparator<T> itemComparator) {
		sortedItemSet = new TreeSet<SortedItem<T>>(itemComparator);
	}

	public SortedSet<SortedItem<T>> getSortedItemSet() {
		return sortedItemSet;
	}

	public void addSortedItem (SortedItem<T> sortedItem){
		sortedItemSet.add(sortedItem);
	}
	
	@Override
	public String toString() {
		Iterator<SortedItem<T>> iterator = sortedItemSet.iterator();
		StringBuilder output = new StringBuilder();
		while (iterator.hasNext()){
			output.append(iterator.next().toString()).append("\n");
		}
		return output.toString();
	}

}

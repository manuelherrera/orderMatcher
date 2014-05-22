package com.ordermatcher.service.rules;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedBook{

	private SortedSet<SortedItem> sortedItemSet;
	
	public SortedBook(ItemModelComparator itemComparator) {
		sortedItemSet = new TreeSet<SortedItem>(itemComparator);
	}

	public SortedSet<SortedItem> getSortedItemSet() {
		return sortedItemSet;
	}

	public void addSortedItem (SortedItem sortedItem){
		sortedItemSet.add(sortedItem);
	}
		
	@Override
	public String toString() {
		Iterator<SortedItem> iterator = sortedItemSet.iterator();
		StringBuilder output = new StringBuilder();
		while (iterator.hasNext()){
			output.append(iterator.next().toString()).append("\n");
		}
		return output.toString();
	}
}

package com.ordermatcher.service.rules;

import java.util.Comparator;

public class ItemModelComparator implements Comparator<SortedItem> {

	@Override
	public int compare(SortedItem item1, SortedItem item2) {
		return item1.getItem().compareTo(item2.getItem());
	}

}

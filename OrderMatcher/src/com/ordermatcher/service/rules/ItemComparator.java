package com.ordermatcher.service.rules;

import java.util.Comparator;

public class ItemComparator<T> implements Comparator<SortedItem<T>> {

	@Override
	public int compare(SortedItem<T> item1, SortedItem<T> item2) {
		return item1.getId().compareTo(item2.getId());
	}

}

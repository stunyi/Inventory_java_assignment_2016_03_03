package org.assignment.dao;

import java.util.LinkedList;
import java.util.List;

import org.assignment.bo.InventoryItem;

public interface InventoryDao {

	/**
	 * Retrieve a {@link LinkedList} of {@link InventoryItem}'s
	 * 
	 * @param filePath
	 * @return List of {@link InventoryItem}
	 * */
	public List<InventoryItem> retrieveInventoryItems(String filePath);
}

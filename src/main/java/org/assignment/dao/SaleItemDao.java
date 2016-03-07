package org.assignment.dao;

import java.util.LinkedList;
import java.util.List;

import org.assignment.bo.SaleItem;

public interface SaleItemDao {

	/**
	 * Retrieve a {@link List} of {@link SaleItem}'s
	 * 
	 * @param filePath
	 * @return List of {@link SaleItem}
	 * */
	public LinkedList<SaleItem> retrieveSalesItems(String filePath);
}

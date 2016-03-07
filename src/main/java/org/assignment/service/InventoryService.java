package org.assignment.service;

import java.util.List;

import org.assignment.bo.InventoryItem;
import org.assignment.bo.SaleItem;

public interface InventoryService {
	
	public List<InventoryItem> getAllStartingInventoryItems();
	
	public List<InventoryItem> getAllCurrentInventoryItems();

	public InventoryItem getStartingInventoryItemByInventoryId(int inventoryId);
	
	public InventoryItem getStartingInventoryItemByProductId(int productId);
	
	public InventoryItem getCurrentInventoryItemByInventoryId(int inventoryId);
	
	public InventoryItem getCurrentInventoryItemByProductId(int productId);
	
	public void updateInventoryLevelsBasedOnSales(List<SaleItem> sales);
}

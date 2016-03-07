package org.assignment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.assignment.ConfigService;
import org.assignment.bo.InventoryItem;
import org.assignment.bo.SaleItem;
import org.assignment.service.InventoryService;

public class InventoryServiceImpl implements InventoryService {
	
	private static List<InventoryItem> startingInventoryItems; //TODO: Not necessary, to be removed
	private static Map<Integer,Double> inventoryTotals;
	private static List<InventoryItem> currentInventoryItems;
	private static String filePath;
	
	public InventoryServiceImpl(){
		//TODO: Not necessary, to be removed
		if(InventoryServiceImpl.startingInventoryItems == null){
			InventoryServiceImpl.startingInventoryItems = new ArrayList<InventoryItem>();
		}
		if(InventoryServiceImpl.currentInventoryItems == null){
			InventoryServiceImpl.currentInventoryItems = new ArrayList<InventoryItem>();
			InventoryServiceImpl.inventoryTotals = new TreeMap<Integer,Double>();
		}
		
		this.setFilePath(getClass().getClassLoader().getResource("InventoryData.csv").getFile());
	}

	public void fillTotalsMap(List<InventoryItem> items){
		for(InventoryItem item : items){
			if(inventoryTotals.get(item.getProductId()) == null){
				inventoryTotals.put(item.getProductId(), new Double(item.getQuantity()));
			} else {
				inventoryTotals.put(item.getProductId(), new Double(inventoryTotals.get(item.getProductId()) + item.getQuantity()));
			}
		}
	}
	
	//TODO: Not necessary, to be removed
	public List<InventoryItem> getAllStartingInventoryItems() {
		return InventoryServiceImpl.startingInventoryItems;
	}

	public List<InventoryItem> getAllCurrentInventoryItems() {
		return InventoryServiceImpl.currentInventoryItems;
	}

	//TODO: Not necessary, to be removed
	public InventoryItem getStartingInventoryItemByInventoryId(int inventoryId) {
		for(InventoryItem inventoryItem : InventoryServiceImpl.startingInventoryItems){
			if(inventoryItem.getInventoryId() == inventoryId){
				return inventoryItem;
			}
		}
		return null;
	}

	//TODO: Not necessary, to be removed
	public InventoryItem getStartingInventoryItemByProductId(int productId) {
		for(InventoryItem inventoryItem : InventoryServiceImpl.startingInventoryItems){
			if(inventoryItem.getProductId() == productId){
				return inventoryItem;
			}
		}
		return null;
	}

	public InventoryItem getCurrentInventoryItemByInventoryId(int inventoryId) {
		
		for(InventoryItem inventoryItem : InventoryServiceImpl.currentInventoryItems){
			if(inventoryItem.getInventoryId() == inventoryId){
				return inventoryItem;
			}
		}
		return null;
	}

	public InventoryItem getCurrentInventoryItemByProductId(int productId) {
		
		List<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
		for(InventoryItem inventoryItem : InventoryServiceImpl.currentInventoryItems){
			if(inventoryItem.getProductId() == productId){
				inventoryItems.add(inventoryItem);
			}
		}
		
		// Sum up the items in Stock
		InventoryItem item = ConfigService.getObjectFactory().createInventoryItem();
		for(InventoryItem inventoryItem : inventoryItems){
			item.setInventoryId(inventoryItem.getInventoryId());
			item.setProductId(inventoryItem.getProductId());
			item.setQuantity(item.getQuantity() + inventoryItem.getQuantity());
			item.setProduct(ConfigService.getObjectFactory().createProductService().getProductById(inventoryItem.getProductId()));
		}
		return inventoryItems.size() != 0 ? item : null;
	}

	public void updateInventoryLevelsBasedOnSales(List<SaleItem> sales) {
		for(SaleItem saleItem : sales){
			InventoryItem inventoryItem = getCurrentInventoryItemByProductId(saleItem.getProductId());
			inventoryItem.subtract(saleItem.getUnitsSold());
			subtract(saleItem.getProductId(),saleItem.getUnitsSold());
		}
	}
	
	public static Double getQuantityByProductId(Integer productId){
		return new Double(InventoryServiceImpl.inventoryTotals.get(productId));
	}

	private void subtract(Integer productId, Double value){
		InventoryServiceImpl.inventoryTotals.put(productId,new Double(InventoryServiceImpl.inventoryTotals.get(productId).doubleValue() - value.doubleValue()));
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		if(InventoryServiceImpl.filePath == null || 
				(InventoryServiceImpl.filePath != null && InventoryServiceImpl.filePath.equals(""))){
			InventoryServiceImpl.currentInventoryItems = ConfigService.getObjectFactory().createInventoryDao().retrieveInventoryItems(filePath);
			InventoryServiceImpl.startingInventoryItems = InventoryServiceImpl.currentInventoryItems; 
			fillTotalsMap(InventoryServiceImpl.currentInventoryItems);
			InventoryServiceImpl.filePath = filePath;
		}
	}

}

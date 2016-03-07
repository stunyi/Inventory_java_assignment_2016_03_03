package org.assignment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.assignment.bo.SaleItem;
import org.assignment.factory.impl.DefaultFactory;
import org.assignment.service.InventoryService;
import org.assignment.service.impl.InventoryServiceImpl;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {

	@Before
	public void configureMe(){
		// Configure the Service Engine
		ConfigService.setObjectFactory(new DefaultFactory());
	}
	
	@Test
	public void testIfAllInventoryItemsWereLoadedFromTheCSV(){
		assertEquals("Inventory Items Fetched", 36 ,ConfigService.getObjectFactory().createInventoryService().getAllCurrentInventoryItems().size());
	}
	
	@Test
	public void testTheStartingQuantityOfAnItem(){		
		assertTrue("Check the beginning Quantity of item '10002' is '4' items ",100 == ConfigService.getObjectFactory().createInventoryService().getAllCurrentInventoryItems().get(0).getQuantity());
	}
	
	@Test
	public void testIfTheInventoryAdjustmentFunctionalityWorkBySellingOneItem(){
		InventoryService inventoryService = ConfigService.getObjectFactory().createInventoryService();
		double initialNoOfItemsInStock = InventoryServiceImpl.getQuantityByProductId(10002).doubleValue();
		
		SaleItem sale = ConfigService.getObjectFactory().createSaleItem();
		sale.setCustomerId(1);
		sale.setUnitsSold(1);
		sale.setProductId(10002);
		
		List<SaleItem> sales = new ArrayList<SaleItem>();
		sales.add(sale);
		
		inventoryService.updateInventoryLevelsBasedOnSales(sales);
		double currentStockQuantity = InventoryServiceImpl.getQuantityByProductId(10002).doubleValue();
		assertTrue("Check if you update the Inventory the quantity changes appropriately ", (initialNoOfItemsInStock - 1) == currentStockQuantity);
	}
}

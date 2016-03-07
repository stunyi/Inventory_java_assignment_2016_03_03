package org.assignment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.assignment.bo.Customer;
import org.assignment.bo.Product;
import org.assignment.factory.impl.DefaultFactory;
import org.junit.Before;
import org.junit.Test;

public class SalesTest {

	@Before
	public void configureMe(){
		// Configure the Service Engine
		ConfigService.setObjectFactory(new DefaultFactory());
	}
	
	@Test
	public void testIfSalesItemsWereFetchedFromCsv(){
		assertEquals("Sales Items Fetched", 199 ,ConfigService.getObjectFactory().createSalesService().getAllItemsSold().size());
	}
	
	@Test
	public void testIfSalesItemsWereFetchedFromCsvWereLoadedAccurately(){
		assertTrue("Confirm that the first Item sold had 4 units", 4 == ConfigService.getObjectFactory().createSalesService().getAllItemsSold().get(0).getUnitsSold());
	}
	
	@Test
	public void testIfTheCalculationForTheTotalSalesToAParticularCustomerAreAccurate(){
		assertTrue("Confirm that the total units purchased for customer '1' is '18' ", 18 == ConfigService.getObjectFactory().createSalesService().getTotalNumberOfItemPurchasedByCustomer(1));		
		assertTrue("Confirm that the total value purchased for customer '1' is '542' ", 542 == ConfigService.getObjectFactory().createSalesService().getTotalValueOfItemPurchasedByCustomer(1));
	}
	
	@Test
	public void testIfTheCalculationForTheTotalSalesPerProductIdAreAccurate(){
		assertTrue("Confirm that the total units purchased with productId '10008' is '12' ", 12 == ConfigService.getObjectFactory().createSalesService().getTotalNumberOfItemsSoldByProductId(10008));		
		assertTrue("Confirm that the total value purchased with productId '10008' is '240' ", 240 == ConfigService.getObjectFactory().createSalesService().getTotalValueOfItemsSoldByProductId(10008));
	}
	
	@Test
	public void testIfTheTotalsForTheProductsSoldAreAccurate(){
		assertTrue("Confirm that the total number of units purchased is '1099' ", 1099 == ConfigService.getObjectFactory().createSalesService().getTotalNumberOfUnitsSold());
		assertTrue("Confirm that the total value of units purchased is '14209' ", 14209 == ConfigService.getObjectFactory().createSalesService().getTotalValueOfUnitsSold());
	}
	
	@Test
	public void testIfTheListingOfTheCustomerInDescendingOrderBasedOnPurchasesIsWorking(){
		
		LinkedList<Customer> customer = ConfigService.getObjectFactory().createSalesService().getInDescendingOrderListOfCustomersBasedOnTotalPurchases();
		
		assertEquals("Ensure that all the customers with Sales are retrieved", 22 ,customer.size());
		assertEquals("Ensure that Customer with CustomerId '11' was the highest", 11 ,customer.get(0).getCustomerId());
	}
	
	@Test
	public void testIfTheListingOfProductsInDescendingOrderBasedOnItemsSoldIsWorking(){
		
		LinkedList<Product> products = ConfigService.getObjectFactory().createSalesService().getInDescendingOrderListOfProductsSold();
		
		assertEquals("Ensure that all the products are retrieved", 36 ,products.size());
		assertEquals("Ensure that Product with ProductId '' has the sales with the most value", 30018 ,products.get(0).getProductID());
	}
	
	
	
}

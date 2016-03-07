package org.assignment;

import static org.junit.Assert.*;

import org.assignment.factory.impl.DefaultFactory;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {
	
	@Before
	public void configureMe(){
		// Configure the Service Engine
		ConfigService.setObjectFactory(new DefaultFactory());
	}

	@Test
	public void testIfProductsFetchedFromCsv(){
		assertEquals("Dao Fetched Data", 36,ConfigService.getObjectFactory().createProductService().getAllProducts().size());
	}
	
	@Test
	public void testIfProductsWereLoadedAccurately(){
		
		assertNotNull("Product with productId 10001",ConfigService.getObjectFactory().createProductService().getProductById(10001));
		assertEquals("Product with productId 10001 should have name 'Claw Hammer 16 oz'","Claw Hammer 16 oz",ConfigService.getObjectFactory().createProductService().getProductById(10001).getDescription());
		assertTrue("Product with productId 10001 should have unit price '17'", 17 == ConfigService.getObjectFactory().createProductService().getProductById(10001).getUnitPrice());
		
	}
	
	
	
}

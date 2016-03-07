/**
 * 
 */
package org.assignment.factory;

import org.assignment.bo.Customer;
import org.assignment.bo.InventoryItem;
import org.assignment.bo.SaleItem;
import org.assignment.dao.InventoryDao;
import org.assignment.dao.ProductDao;
import org.assignment.dao.SaleItemDao;
import org.assignment.service.CustomerService;
import org.assignment.service.InventoryService;
import org.assignment.service.ProductService;
import org.assignment.service.SalesService;

/**
 * Object Factory Interface
 */
public interface IFactory {

	/**
	 * Method for creating {@link Customer} Object
	 * 
	 * @return {@link Customer}
	 * */
	public Customer createCustomer();

	/**
	 * Method for creating {@link SaleItem}
	 * 
	 * @return {@link SaleItem}
	 * */
	public SaleItem createSaleItem();
	
	/**
	 * Method for creating {@link InventoryItem}
	 * 
	 * @return {@link InventoryItem}
	 * */
	public InventoryItem createInventoryItem();

	public ProductService createProductService();
	
	public InventoryService createInventoryService();
	
	public SalesService createSalesService();
	
	public CustomerService createCustomerService();
	
	public ProductDao createProductDao();
	
	public SaleItemDao createSaleItemDao();
	
	public InventoryDao createInventoryDao();
}

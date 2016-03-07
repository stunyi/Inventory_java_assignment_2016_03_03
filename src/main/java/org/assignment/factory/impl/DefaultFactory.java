package org.assignment.factory.impl;

import org.assignment.bo.Customer;
import org.assignment.bo.InventoryItem;
import org.assignment.bo.SaleItem;
import org.assignment.dao.InventoryDao;
import org.assignment.dao.ProductDao;
import org.assignment.dao.SaleItemDao;
import org.assignment.dao.impl.InventoryDaoCSVImpl;
import org.assignment.dao.impl.ProductDaoCSVImpl;
import org.assignment.dao.impl.SalesItemDaoCSVImpl;
import org.assignment.factory.IFactory;
import org.assignment.service.CustomerService;
import org.assignment.service.InventoryService;
import org.assignment.service.ProductService;
import org.assignment.service.SalesService;
import org.assignment.service.impl.CustomerServiceImpl;
import org.assignment.service.impl.InventoryServiceImpl;
import org.assignment.service.impl.ProductServiceImpl;
import org.assignment.service.impl.SalesServiceImpl;

public class DefaultFactory implements IFactory {

	/**
	 * (non-Javadoc)
	 * @see org.assignment.factory.IFactory#createCustomer()
	 */
	public Customer createCustomer() {
		return new Customer();
	}

	/**
	 * (non-Javadoc)
	 * @see org.assignment.factory.IFactory#createSaleItem()
	 */
	public SaleItem createSaleItem() {
		return new SaleItem();
	}

	/**
	 * (non-Javadoc)
	 * @see org.assignment.factory.IFactory#createInventoryItem()
	 */
	public InventoryItem createInventoryItem() {
		return new InventoryItem();
	}

	public ProductService createProductService() {
		return new ProductServiceImpl();
	}

	
	public InventoryService createInventoryService() {
		return new InventoryServiceImpl();
	}

	
	public SalesService createSalesService() {
		return new SalesServiceImpl();
	}

	public CustomerService createCustomerService() {
		return new CustomerServiceImpl();
	}

	public ProductDao createProductDao() {
		return new ProductDaoCSVImpl();
	}
	

	public SaleItemDao createSaleItemDao() {
		return new SalesItemDaoCSVImpl();
	}
	

	public InventoryDao createInventoryDao() {
		return new InventoryDaoCSVImpl();
	}
}

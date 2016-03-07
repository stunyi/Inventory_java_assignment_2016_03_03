package org.assignment.service;

import java.util.LinkedList;

import org.assignment.bo.Customer;
import org.assignment.bo.Product;
import org.assignment.bo.SaleItem;

public interface SalesService {

	public SaleItem getSaleItemBySalesId(int salesId);
	
	public LinkedList<SaleItem> getAllItemsSold();
	
	public LinkedList<SaleItem> getAllItemsSoldToCustomer(int customerId);
	
	public LinkedList<SaleItem> getAllProductsPurchasedByProductId(int productId);
	
	public LinkedList<Customer> getInDescendingOrderListOfCustomersBasedOnTotalPurchases();
	
	public LinkedList<Product> getInDescendingOrderListOfProductsSold();
	
	public double getTotalNumberOfItemPurchasedByCustomer(int customerId);
	
	public double getTotalValueOfItemPurchasedByCustomer(int customerId);
	
	public double getTotalNumberOfItemsSoldByProductId(int productId);
	
	public double getTotalValueOfItemsSoldByProductId(int productId);
	
	public double getTotalNumberOfUnitsSold();
	
	public double getTotalValueOfUnitsSold();
}

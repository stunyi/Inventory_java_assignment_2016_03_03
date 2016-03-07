package org.assignment.service.impl;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import org.assignment.ConfigService;
import org.assignment.bo.Customer;
import org.assignment.bo.Product;
import org.assignment.bo.SaleItem;
import org.assignment.service.SalesService;

public class SalesServiceImpl implements SalesService {

	private static LinkedList<SaleItem> sales;
	private String filePath;

	public SalesServiceImpl(){
		this.setFilePath(getClass().getClassLoader().getResource("SalesData.csv").getFile());
	}
	
	public double getTotalNumberOfUnitsSold() {
		double totals = 0;
		for(SaleItem saleItem : this.getAllItemsSold()){
			totals += saleItem.getUnitsSold();
		}
		return totals;
	}

	public double getTotalValueOfUnitsSold() {
		double totals = 0;
		for(SaleItem saleItem : this.getAllItemsSold()){
			totals += saleItem.getSaleTotal();
		}
		return totals;
	}
	
	public double getTotalNumberOfItemPurchasedByCustomer(int customerId) {
		double totals = 0;
		for(SaleItem saleItem : getAllItemsSoldToCustomer(customerId)){
			totals += saleItem.getUnitsSold();
		}
		return totals;
	}
	
	public double getTotalValueOfItemPurchasedByCustomer(int customerId) {
		double totals = 0;
		for(SaleItem saleItem : getAllItemsSoldToCustomer(customerId)){
			totals += saleItem.getSaleTotal();
		}
		return totals;
	}
	
	public double getTotalNumberOfItemsSoldByProductId(int productId) {
		double totals = 0;
		for(SaleItem saleItem : this.getAllProductsPurchasedByProductId(productId)){
			totals += saleItem.getUnitsSold();
		}
		return totals;
	}

	public double getTotalValueOfItemsSoldByProductId(int productId) {
		double totals = 0;
		for(SaleItem saleItem : this.getAllProductsPurchasedByProductId(productId)){
			totals += saleItem.getSaleTotal();
		}
		return totals;
	}
	
	@Deprecated
	public SaleItem getSaleItemBySalesId(int salesId) {
		/*for(SaleItem saleItem : SalesServiceImpl.sales){
			if(saleItem.get)
		}*/
		return null;
	}

	public LinkedList<SaleItem> getAllProductsPurchasedByProductId(int productId) {
		
		LinkedList<SaleItem> allSalesWithProductId = new LinkedList<SaleItem>();
		
		for(SaleItem sale : SalesServiceImpl.sales){
			if(sale.getProductId() == productId)
				allSalesWithProductId.add(sale);
		}
		return allSalesWithProductId;
	}

	public LinkedList<SaleItem> getAllItemsSold() {
		return SalesServiceImpl.sales;
	}

	public LinkedList<SaleItem> getAllItemsSoldToCustomer(int customerId) {
		
		LinkedList<SaleItem> itemsSoldToCustomerId = new LinkedList<SaleItem>();
		
		for(SaleItem saleItem : SalesServiceImpl.sales){
			if(saleItem.getCustomerId() == customerId){
				itemsSoldToCustomerId.add(saleItem);
			}
		}
		return itemsSoldToCustomerId;
	}

	public LinkedList<Customer> getInDescendingOrderListOfCustomersBasedOnTotalPurchases() {
		
		Map<Integer, Double> customerTotals = new TreeMap<Integer, Double>();
		
		for(Customer customer : ConfigService.getObjectFactory().createCustomerService().getAllCustomers()){
			
			for(SaleItem saleItem : this.getAllItemsSoldToCustomer(customer.getCustomerId())){
				if(customerTotals.get(customer.getCustomerId()) == null){
					customerTotals.put(customer.getCustomerId(), 
							saleItem.getSaleTotal());
				}else{
					customerTotals.put(customer.getCustomerId(), 
							customerTotals.get(customer.getCustomerId()) + saleItem.getSaleTotal());
				}
			}
		}		
		
		Integer[] customerNumbers = new Integer[customerTotals.keySet().size()];
		Double[] customerPurchases = new Double[customerTotals.keySet().size()];
		
		// Transfer values to Arrays
		int i = 0;
		for(Integer customerNumber : customerTotals.keySet()){
			customerNumbers[i] = customerNumber;
			customerPurchases[i] = customerTotals.get(customerNumber);
			i++;
		}
		
		// Sort the Arrays in descending order based on Value of Purchases
		for(int j = 0; j < customerPurchases.length; j++){
			for(int k = 0; k < customerPurchases.length; k++){
				if(customerPurchases[j] < customerPurchases[k]){
					double tempValue = customerPurchases[j];
					customerPurchases[j] = customerPurchases[k];
					customerPurchases[k] = tempValue;
					
					int tempCustId = customerNumbers[j];
					customerNumbers[j] = customerNumbers[k];
					customerNumbers[k] = tempCustId;
				}
			}
		}
		LinkedList<Customer> customers = new LinkedList<Customer>();
		for(int j = 0; j < customerNumbers.length; j++){
			customers.add(ConfigService.getObjectFactory().createCustomerService().getCustomerById(customerNumbers[j]));
		}
		
		return customers;
	}

	public LinkedList<Product> getInDescendingOrderListOfProductsSold() {
		
		Map<Integer, Double> productTotals = new TreeMap<Integer, Double>();
		
		for(Product product : ConfigService.getObjectFactory().createProductService().getAllProducts()){
			
			for(SaleItem saleItem : this.getAllProductsPurchasedByProductId(product.getProductID())){
				if(productTotals.get(product.getProductID()) == null){
					productTotals.put(product.getProductID(), 
							saleItem.getSaleTotal());
				}else{
					productTotals.put(product.getProductID(), 
							productTotals.get(product.getProductID()) + saleItem.getSaleTotal());
				}
			}
		}		
		
		Integer[] productIds = new Integer[productTotals.keySet().size()];
		Double[] productsTotals = new Double[productTotals.keySet().size()];
		
		// Transfer values to Arrays
		int i = 0;
		for(Integer productId : productTotals.keySet()){
			productIds[i] = productId;
			productsTotals[i] = productTotals.get(productId);
			i++;
		}
		
		// Sort the Arrays in descending order based on Value of Purchases
		for(int j = 0; j < productsTotals.length; j++){
			for(int k = 0; k < productsTotals.length; k++){
				if(productsTotals[j] < productsTotals[k]){
					double tempValue = productsTotals[j];
					productsTotals[j] = productsTotals[k];
					productsTotals[k] = tempValue;
					
					int tempCustId = productIds[j];
					productIds[j] = productIds[k];
					productIds[k] = tempCustId;
				}
			}
		}
		LinkedList<Product> products = new LinkedList<Product>();
		for(int j = 0; j < productIds.length; j++){
			products.add(ConfigService.getObjectFactory().createProductService().getProductById(productIds[j]));
		}
		
		return products;

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
		this.filePath = filePath;
		SalesServiceImpl.sales = ConfigService.getObjectFactory().createSaleItemDao().retrieveSalesItems(filePath);
	}

	


}

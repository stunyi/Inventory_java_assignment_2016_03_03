package org.assignment.bo;

import org.assignment.ConfigService;

/**
 * SaleItem Business Object
 */
public class SaleItem {

	private int productId;
	private int customerId;
	private Product product;
	private Customer customer;
	private double unitsSold;
	
	public SaleItem(){
		this.product = null;
		this.customer = null;
		this.unitsSold = 0;
	}

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * @return the product
	 */
	public Product getProduct() {
		if(this.product == null){
			this.product = ConfigService.getObjectFactory().createProductService().getProductById(getProductId());
		}
		return this.product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		if(this.customer == null){
			this.customer = ConfigService.getObjectFactory().createCustomerService().getCustomerById(getCustomerId());
			// Just In case Customer Doesn't exist
			// Create the rich fellow
			if(this.customer == null){
				ConfigService.getObjectFactory().createCustomerService().createCustomer(customerId, String.valueOf(customerId));
				this.customer = ConfigService.getObjectFactory().createCustomerService().getCustomerById(getCustomerId());
			}
		}
		return this.customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the unitsSold
	 */
	public double getUnitsSold() {
		return unitsSold;
	}

	/**
	 * @param unitsSold the unitsSold to set
	 */
	public void setUnitsSold(double unitsSold) {
		this.unitsSold = unitsSold;
	}
	
	public double getSaleTotal(){
		return this.getUnitsSold() * this.getProduct().getUnitPrice();
	}
	
}

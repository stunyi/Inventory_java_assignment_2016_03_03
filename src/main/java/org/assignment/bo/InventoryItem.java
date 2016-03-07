package org.assignment.bo;

/**
 * InventoryItem Business Object
 */
public class InventoryItem {

	private int inventoryId;
	private int productId;
	private Product product;
	private Double quantity;
	
	public InventoryItem(){
		this.inventoryId = 0;
		this.productId = 0;
		this.quantity = new Double(0);
		this.product = null;
	}

	/**
	 * @return the inventoryId
	 */
	public int getInventoryId() {
		return inventoryId;
	}

	/**
	 * @param inventoryId the inventoryId to set
	 */
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity.doubleValue();
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = new Double(quantity);
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
	
	public void subtract(double items){
		double quantity = this.getQuantity() - items;
		this.setQuantity(quantity);
	}
	
}

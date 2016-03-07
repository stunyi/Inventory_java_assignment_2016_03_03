package org.assignment.service.impl;

import java.util.LinkedList;

import org.assignment.ConfigService;
import org.assignment.bo.Product;
import org.assignment.service.ProductService;

public class ProductServiceImpl implements ProductService {
	
	private static LinkedList<Product> products;
	private String filePath;
	
	public ProductServiceImpl(){
		this.setFilePath(getClass().getClassLoader().getResource("ProductData.csv").getFile());
	}

	public Product getProductById(int productId) {
		for(Product product: ProductServiceImpl.products){
			if(product.getProductID() == productId){
				return product;
			}
		}
		return null;
	}

	public LinkedList<Product> getAllProducts() {
		return ProductServiceImpl.products;
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
		ProductServiceImpl.products = ConfigService.getObjectFactory().createProductDao().retrieveProducts(filePath);
		this.filePath = filePath;
	}

}

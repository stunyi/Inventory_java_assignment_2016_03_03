package org.assignment.service;

import java.util.LinkedList;

import org.assignment.bo.Product;

public interface ProductService {

	public Product getProductById(int productId);
	
	public LinkedList<Product> getAllProducts();
}

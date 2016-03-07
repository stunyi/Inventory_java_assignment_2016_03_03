package org.assignment.dao;

import java.util.LinkedList;
import java.util.List;

import org.assignment.bo.Product;

public interface ProductDao {

	/**
	 * Retrieve a {@link List} of {@link Product}'s
	 * 
	 * @param filePath
	 * @return List of {@link Product}
	 * */
	public LinkedList<Product> retrieveProducts(String filePath);
}

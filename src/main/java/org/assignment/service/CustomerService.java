package org.assignment.service;

import java.util.LinkedList;

import org.assignment.bo.Customer;

public interface CustomerService {

	public Customer getCustomerById(int customerId);
	
	public void createCustomer(int customerId, String customerName);
	
	public LinkedList<Customer> getAllCustomers();
	
}

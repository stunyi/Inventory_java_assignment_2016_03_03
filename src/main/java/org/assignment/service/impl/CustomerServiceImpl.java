package org.assignment.service.impl;

import java.util.LinkedList;

import org.assignment.ConfigService;
import org.assignment.bo.Customer;
import org.assignment.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	private static LinkedList<Customer> customers;
	
	public CustomerServiceImpl(){
		if(CustomerServiceImpl.customers == null){
			CustomerServiceImpl.customers = new LinkedList<Customer>();
		}
	}
	
	public Customer getCustomerById(int customerId) {
		for(Customer customer : CustomerServiceImpl.customers){
			if(customer.getCustomerId() == customerId){
				return customer;
			}
		}
		return null;
	}

	public void createCustomer(int customerId, String customerName) {
		if(this.getCustomerById(customerId) == null){
			Customer customer = ConfigService.getObjectFactory().createCustomer();
			customer.setCustomerId(customerId);
			customer.setCustomerName(customerName);
			CustomerServiceImpl.customers.add(customer);
		}
	}

	public LinkedList<Customer> getAllCustomers() {
		return CustomerServiceImpl.customers;
	}

}

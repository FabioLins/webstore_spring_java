package org.packt.webstore.service;

import java.util.List;

import org.packt.webstore.domain.Customer;

public interface CustomerService {

	List<Customer> getAllCustomers();
	
	void addCustomer(Customer customer);
	
}

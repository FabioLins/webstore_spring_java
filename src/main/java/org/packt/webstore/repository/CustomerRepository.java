package org.packt.webstore.repository;

import java.util.List;

import org.packt.webstore.domain.Customer;

public interface CustomerRepository {

	List<Customer> getAllCustomers();
	
	void addCustomer(Customer customer);
	
}

package org.packt.webstore.repository;

import org.packt.webstore.domain.Customer;

public interface CustomerRepository {

	Long saveCustomer(Customer customer);
	
	Customer getCustomer(String customerId);
	
	Boolean isCustomerExist(String customerId);
	
}

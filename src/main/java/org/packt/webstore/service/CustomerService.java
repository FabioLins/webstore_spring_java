package org.packt.webstore.service;

import org.packt.webstore.domain.Customer;

public interface CustomerService {

	Long saveCustomer(Customer customer);
	
	Customer getCustomer(String customerId);
	
	Boolean isCustomerExist(String customerId);
	
}

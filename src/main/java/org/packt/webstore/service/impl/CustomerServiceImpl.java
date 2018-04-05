package org.packt.webstore.service.impl;

import org.packt.webstore.domain.Customer;
import org.packt.webstore.repository.CustomerRepository;
import org.packt.webstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Long saveCustomer(Customer customer) {
		return this.customerRepository.saveCustomer(customer);
	}

	@Override
	public Customer getCustomer(String customerId) {
		return this.customerRepository.getCustomer(customerId);
	}

	@Override
	public Boolean isCustomerExist(String customerId) {
		return this.customerRepository.isCustomerExist(customerId);
	}

}

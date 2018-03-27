package org.packt.webstore.service.impl;

import java.util.List;

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
	public List<Customer> getAllCustomers() {
		return this.customerRepository.getAllCustomers();
	}

	@Override
	public void addCustomer(Customer customer) {
		this.customerRepository.addCustomer(customer);
	}

}

package org.packt.webstore.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.packt.webstore.domain.Customer;
import org.packt.webstore.service.AddressService;
import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<Customer> {
	
	private AddressService addressService;

	public CustomerMapper(AddressService addressService) {
		this.addressService = addressService;
	}

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Customer customer = new Customer();
		customer.setCustomerId(rs.getLong("ID"));
		customer.setName(rs.getString("NAME"));
		customer.setBillingAddress(this.addressService.getAddressById(rs.getString("BILLING_ADDRESS_ID")));
		customer.setPhoneNumber(rs.getString("PHONE_NUMBER"));
		
		return customer;
	}

}

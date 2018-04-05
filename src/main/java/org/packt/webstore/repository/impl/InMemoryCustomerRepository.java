package org.packt.webstore.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.packt.webstore.domain.Customer;
import org.packt.webstore.repository.CustomerRepository;
import org.packt.webstore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private AddressService addressService;

	@Override
	public Long saveCustomer(Customer customer) {
		
			long addressId = this.addressService.saveAddress(customer.getBillingAddress());
			
			String SQL = "INSERT INTO CUSTOMER(NAME, PHONE_NUMBER, BILLING_ADDRESS_ID)"
					+ " VALUES (:name, :phoneNumber, :addressId)";
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", customer.getName());
			params.put("phoneNumber", customer.getPhoneNumber());
			params.put("addressId", addressId);
			
			SqlParameterSource paramSource = new MapSqlParameterSource(params);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			this.jdbcTemplate.update(SQL, paramSource, keyHolder, new String[] {"ID"});
			
			return keyHolder.getKey().longValue();
			
	}
	
	@Override
	public Customer getCustomer(String customerId) {
		
		String SQL = "SELECT * FROM CUSTOMER WHERE ID = :customerId";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerId", customerId);
		
		Customer customer = this.jdbcTemplate.queryForObject(SQL, params, new CustomerMapper(this.addressService));
		
		return customer;
	
	}

	@Override
	public Boolean isCustomerExist(String customerId) {
		
		String SQL = "SELECT * FROM CUSTOMER WHERE ID = :customerId";
		
		Map<String, Object> params = new HashMap<String, Object>(); 
		params.put("customerId", customerId);
		
		try {
			this.jdbcTemplate.queryForObject(SQL, params, new CustomerMapper(this.addressService));
		} catch(EmptyResultDataAccessException e) {
			return false;
		}
		
		return true;
	}

}

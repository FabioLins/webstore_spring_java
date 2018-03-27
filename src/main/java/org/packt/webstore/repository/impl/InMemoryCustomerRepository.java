package org.packt.webstore.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.packt.webstore.domain.Customer;
import org.packt.webstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate JdbcTemplate;

	@Override
	public List<Customer> getAllCustomers() {
		
		Map<String, Object> params = new HashMap<String, Object>(); 
		List<Customer> customers = this.JdbcTemplate.query("SELECT * FROM Customers", params, new CustomerMapper());
		return customers;
	
	}
	
	private static final class CustomerMapper implements RowMapper<Customer> {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

			Customer customer = new Customer();
			customer.setCustomerId(rs.getString("ID"));
			customer.setName(rs.getString("NAME"));
			customer.setAddress(rs.getString("ADDRESS"));
			customer.setNoOfOrdersMade(rs.getLong("NO_ORDERS_MADE"));
			
			return customer;
		}
	
	}

	@Override
	public void addCustomer(Customer customer) {
		String SQL = "INSERT INTO CUSTOMERS (ID,"  
				+ "	NAME,"  
				+ "	ADDRESS,"  
				+ "	NO_ORDERS_MADE) VALUES (:id, :name, :address, :noOrderMade)";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customer.getCustomerId());
		params.put("name", customer.getName());
		params.put("address", customer.getAddress());
		params.put("noOrderMade", customer.getNoOfOrdersMade());
		
		this.JdbcTemplate.update(SQL, params);
		
	}

}

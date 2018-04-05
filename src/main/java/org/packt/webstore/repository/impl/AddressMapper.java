package org.packt.webstore.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.packt.webstore.domain.Address;
import org.springframework.jdbc.core.RowMapper;

public class AddressMapper implements RowMapper<Address> {

	@Override
	public Address mapRow(ResultSet rs, int rowNum) throws SQLException {

		Address address = new Address();
		address.setId(rs.getLong("ID"));
		address.setDoorNo(rs.getString("DOOR_NO"));
		address.setStreetName(rs.getString("STREET_NAME"));
		address.setAreaName(rs.getString("AREA_NAME"));
		address.setState(rs.getString("STATE"));
		address.setCountry(rs.getString("COUNTRY"));
		address.setZipCode(rs.getString("ZIP"));
		
		return address;
	}

	
	
}

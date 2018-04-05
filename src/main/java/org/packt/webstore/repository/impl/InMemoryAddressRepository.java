package org.packt.webstore.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.packt.webstore.domain.Address;
import org.packt.webstore.exception.AddressNotFoundException;
import org.packt.webstore.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryAddressRepository implements AddressRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public Address getAddressById(String addressId) {
		
		String SQL = "SELECT * FROM ADDRESS WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", addressId);
		try {
			return this.jdbcTemplate.queryForObject(SQL, params, new AddressMapper());
		} catch(DataAccessException e) {
			throw new AddressNotFoundException(addressId);
		}
		
	}

	@Override
	public Long saveAddress(Address address) {
		
		String SQL = "INSERT INTO ADDRESS(DOOR_NO, STREET_NAME, AREA_NAME, STATE, COUNTRY, ZIP)"
				+ " VALUES (:doorNo, :streetName, :areaName, :state, :country, :zip)";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("doorNo", address.getDoorNo());
		params.put("streetName", address.getStreetName());
		params.put("areaName", address.getAreaName());
		params.put("state", address.getState());
		params.put("country", address.getCountry());
		params.put("zip", address.getZipCode());
		
		SqlParameterSource paramSource = new MapSqlParameterSource(params);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		this.jdbcTemplate.update(SQL, paramSource, keyHolder, new String[] {"ID"});
		
		return keyHolder.getKey().longValue();
		
	}

}

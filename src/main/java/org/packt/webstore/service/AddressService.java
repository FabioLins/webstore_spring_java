package org.packt.webstore.service;

import org.packt.webstore.domain.Address;

public interface AddressService {
	
	Address getAddressById(String addressId);
	
	Long saveAddress(Address address);

}

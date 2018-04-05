package org.packt.webstore.repository;

import org.packt.webstore.domain.Address;

public interface AddressRepository {
	
	Address getAddressById(String addressId);
	
	Long saveAddress(Address address);

}

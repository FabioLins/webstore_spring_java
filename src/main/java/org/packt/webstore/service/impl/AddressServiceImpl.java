package org.packt.webstore.service.impl;

import org.packt.webstore.domain.Address;
import org.packt.webstore.repository.AddressRepository;
import org.packt.webstore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Address getAddressById(String addressId) {
		return this.addressRepository.getAddressById(addressId);
	}

	@Override
	public Long saveAddress(Address address) {
		return this.addressRepository.saveAddress(address);
	}

}

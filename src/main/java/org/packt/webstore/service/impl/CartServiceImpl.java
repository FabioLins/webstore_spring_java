package org.packt.webstore.service.impl;

import org.packt.webstore.domain.Cart;
import org.packt.webstore.dto.CartDto;
import org.packt.webstore.repository.CartRepository;
import org.packt.webstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public void create(CartDto cartDto) {
		this.cartRepository.create(cartDto);
	}

	@Override
	public Cart read(String id) {
		return this.cartRepository.read(id);
	}

	@Override
	public void update(String id, CartDto cartDto) {
		this.cartRepository.update(id, cartDto);
	}

	@Override
	public void delete(String id) {
		this.cartRepository.delete(id);
	}

	@Override
	public void addItem(String cartId, String productId) {
		this.cartRepository.addItem(cartId, productId);
	}

	@Override
	public void removeItem(String cartId, String productId) {
		this.cartRepository.removeItem(cartId, productId);
	}

}

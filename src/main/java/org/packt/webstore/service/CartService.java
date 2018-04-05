package org.packt.webstore.service;

import org.packt.webstore.domain.Cart;
import org.packt.webstore.dto.CartDto;

public interface CartService {
	
	void create(CartDto cartDto);
	Cart read(String id);
	void update(String id, CartDto cartDto);
	void delete(String id);
	
	void addItem(String cartId, String productId);
	void removeItem(String cartId, String productId);
	Cart validate(String cartId);
	void clearCart(String cartId);
}

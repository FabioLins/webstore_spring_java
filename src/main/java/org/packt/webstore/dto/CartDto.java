package org.packt.webstore.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartDto implements Serializable {

	private static final long serialVersionUID = 6731892562762708680L;

	private String id;
	
	private List<CartItemDto> cartItems;

	public CartDto() {
	}

	public CartDto(String id) {
		this.id = id;
		this.cartItems = new ArrayList<CartItemDto>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CartItemDto> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemDto> cartItems) {
		this.cartItems = cartItems;
	}
	
	public void addCartItem(CartItemDto cartItemDto) {
		this.cartItems.add(cartItemDto);
	}
	
}

package org.packt.webstore.exception;

public class InvalidCartException extends RuntimeException {

	private static final long serialVersionUID = 6521124924602643686L;

	private String cartId;

	public InvalidCartException(String cartId) {
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}
	
}

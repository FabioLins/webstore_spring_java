package org.packt.webstore.exception;

public class AddressNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3383143636689185874L;
	
	private String productId;

	public AddressNotFoundException(String productId) {
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}
	
}

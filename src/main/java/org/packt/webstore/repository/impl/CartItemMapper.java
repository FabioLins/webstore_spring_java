package org.packt.webstore.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.packt.webstore.domain.CartItem;
import org.packt.webstore.service.ProductService;
import org.springframework.jdbc.core.RowMapper;

public class CartItemMapper implements RowMapper<CartItem> {

	private ProductService productService;
	
	public CartItemMapper(ProductService productService) {
		this.productService = productService;
	}
	
	@Override
	public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CartItem cartItem = new CartItem(rs.getString("ID"));
		cartItem.setProduct(this.productService.getProductById(rs.getString("PRODUCT_ID")));
		cartItem.setQuantity(rs.getInt("QUANTITY"));
		
		return cartItem;
		
	}

}

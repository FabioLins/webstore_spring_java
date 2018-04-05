package org.packt.webstore.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.packt.webstore.domain.Cart;
import org.packt.webstore.domain.CartItem;
import org.packt.webstore.domain.Product;
import org.packt.webstore.dto.CartDto;
import org.packt.webstore.dto.CartItemDto;
import org.packt.webstore.repository.CartRepository;
import org.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCartRepository implements CartRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public void create(CartDto cartDto) {
		
		String INSERT_CART_SQL = "INSERT INTO CART(ID) VALUES (:id)";

		Map<String, Object> cartParams = new HashMap<String, Object>();
		cartParams.put("id", cartDto.getId());
		
		this.jdbcTemplate.update(INSERT_CART_SQL, cartParams);
		
		cartDto.getCartItems().stream().forEach(cartItemDto -> {
			
			Product productById = this.productService.getProductById(cartItemDto.getProductId());
			String INSERT_CART_ITEM_SQL = "INSERT INTO CART_ITEM(ID, PRODUCT_ID, CART_ID, QUANTITY) "
					+ "VALUES (:id, :product_id, :cart_id, :quantity)";
			
			Map<String, Object> cartItemParams = new HashMap<String, Object>();
			cartItemParams.put("id", cartItemDto.getId());
			cartItemParams.put("product_id", productById.getProductId());
			cartItemParams.put("cart_id", cartDto.getId());
			cartItemParams.put("quantity", cartItemDto.getQuantity());

			this.jdbcTemplate.update(INSERT_CART_ITEM_SQL, cartItemParams);
			
		});
		
	}

	@Override
	public Cart read(String id) {
		String SQL = "SELECT * FROM CART WHERE ID = :id";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		CartMapper cartMapper = new CartMapper(this.jdbcTemplate, this.productService);
		
		try {
			return this.jdbcTemplate.queryForObject(SQL, params, cartMapper);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public void update(String id, CartDto cartDto) {
		
		List<CartItemDto> cartItems = cartDto.getCartItems();
		
		for (CartItemDto cartItem : cartItems) {
			
			String SQL = "UPDATE CART_ITEM SET QUANTITY = :quantity, PRODUCT_ID = :productId"
					+ " WHERE ID = :id AND CART_ID = :cartId";
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", cartItem.getId());
			params.put("quantity", cartItem.getQuantity());
			params.put("productId", cartItem.getProductId());
			params.put("cartId", id);
			
			this.jdbcTemplate.update(SQL, params);
			
		}

	}

	@Override
	public void delete(String id) {
		
		String SQL_DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE CART_ID = :id";
			
		String SQL_DELETE_CART = "DELETE FROM CART WHERE ID = :id";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		this.jdbcTemplate.update(SQL_DELETE_CART_ITEM, params);
		this.jdbcTemplate.update(SQL_DELETE_CART, params);
		
		
	}

	@Override
	public void addItem(String cartId, String productId) {
		
		String SQL = null;
		Cart cart = null;
		
		cart = this.read(cartId);
		
		if(cart == null) {
			
			CartItemDto newCartItemDto = new CartItemDto();
			newCartItemDto.setId(cartId + productId);
			newCartItemDto.setProductId(productId);
			newCartItemDto.setQuantity(1);
			
			CartDto newCartDto = new CartDto(cartId);
			newCartDto.addCartItem(newCartItemDto);
			this.create(newCartDto);
			return;
			
		}
		
		Map<String, Object> cartItemsParams = new HashMap<String, Object>();
		
		if(cart.getItemByProductId(productId) == null) {
			SQL = "INSERT INTO CART_ITEM (ID, PRODUCT_ID, CART_ID, QUANTITY) "
					+ "VALUES (:id, :productId, :cartId, :quantity)";
			
			cartItemsParams.put("id", cartId+productId);
	        cartItemsParams.put("quantity", 1);
		
		} else {
			SQL = "UPDATE CART_ITEM SET QUANTITY = :quantity WHERE CART_ID = :cartId "
					+ "AND PRODUCT_ID = :productId";
		
			CartItem existingItem = cart.getItemByProductId(productId);
			cartItemsParams.put("id", existingItem.getId());
			cartItemsParams.put("quantity", existingItem.getQuantity()+1);
		}
		
		cartItemsParams.put("productId", productId);
		cartItemsParams.put("cartId", cartId);
		
		this.jdbcTemplate.update(SQL, cartItemsParams);
	
	}

	@Override
	public void removeItem(String cartId, String productId) {
		
		String SQL_DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE PRODUCT_ID = :productId"
				+ " AND CART_ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", cartId);
		params.put("productId", productId);
		
		this.jdbcTemplate.update(SQL_DELETE_CART_ITEM, params);

	}

	@Override
	public void clearCart(String cartId) {
		
		String SQL_DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE CART_ID = :id";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", cartId);
		
		this.jdbcTemplate.update(SQL_DELETE_CART_ITEM, params);
		
	}

}

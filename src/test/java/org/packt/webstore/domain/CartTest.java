package org.packt.webstore.domain;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class CartTest {
	
	private Cart cart;
	
	@Before
	public void setUp() {
		this.cart = new Cart("1");
	}

	@Test
	public void cart_grand_total_should_be_equal_to_products_sum_values_cart_items() {
		//Arrange
		Product iphone = new Product("P1234", "iPhone5s", new BigDecimal(500));
		Product dellInspiron = new Product("P1235", "Dell Inspiron", new BigDecimal(700));
		Product nexus7 = new Product("P1236", "Nexus 7", new BigDecimal(300));
		
		this.cart.getCartItems().add(new CartItem("1", iphone));
		this.cart.getCartItems().add(new CartItem("2", dellInspiron));
		this.cart.getCartItems().add(new CartItem("3", nexus7));
		
		//Act
		
		BigDecimal grandTotal = this.cart.getGrandTotal();
		
		//Assert
		Assert.assertEquals(new BigDecimal(1500), grandTotal);
		
	}
	
}

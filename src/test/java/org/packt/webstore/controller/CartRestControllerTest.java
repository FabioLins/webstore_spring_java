package org.packt.webstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.webstore.config.WebApplicationContextConfig;
import org.packt.webstore.dto.CartDto;
import org.packt.webstore.dto.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=WebApplicationContextConfig.class)
@WebAppConfiguration
public class CartRestControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	MockHttpSession session;
	
	private MockMvc mockMvc;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	//@Test
	public void create_method_should_return_create() throws Exception {
		
		//Arrange
		CartItemDto cartItemDto = new CartItemDto(session.getId() + "P1234", "P1234", 2);
		CartDto cartDto = new CartDto(session.getId());
		cartDto.getCartItems().add(cartItemDto);
		
		//Act & Assert
		this.mockMvc.perform(post("/rest/cart").contentType(contentType).content(asJsonString(cartDto)))
			.andExpect(status().isCreated());
	}
	
	//@Test
	public void read_method_should_return_correct_cart_Json_object() throws Exception {
		
		//Arrange
		this.mockMvc.perform(put("/rest/cart/add/P1234").session(session)).andExpect(status().is(200));
		
		//Act
		this.mockMvc.perform(get("/rest/cart/" + session.getId()).session(session))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.cartItems[0].product.productId").value("P1234"));
		
	}
	
	//@Test
	public void update_method_should_update_cart() throws Exception {
		
		//Arrange
		CartItemDto cartItemDto = new CartItemDto(session.getId() + "P1234", "P1234", 2);
		CartDto cartDto = new CartDto(session.getId());
		cartDto.getCartItems().add(cartItemDto);
		
		this.mockMvc.perform(put("/rest/cart/add/P1234").session(session)).andExpect(status().is(200));
		
		//Act
		this.mockMvc.perform(put("/rest/cart/" + session.getId()).session(session).contentType(contentType).content(asJsonString(cartDto)))
			.andExpect(status().isOk());
		
		//Assert
		this.mockMvc.perform(get("/rest/cart/" + session.getId()).session(session))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.cartItems[0].product.productId").value("P1234"))
			.andExpect(jsonPath("$.cartItems[0].quantity").value(2));
	
	}
	
	//@Test
	public void delete_method_should_return_ok() throws Exception {
		
		//Arrange
		CartItemDto cartItemDto = new CartItemDto(session.getId() + "P1234", "P1234", 2);
		CartDto cartDto = new CartDto(session.getId());
		cartDto.getCartItems().add(cartItemDto);
		
		this.mockMvc.perform(post("/rest/cart").contentType(contentType).content(asJsonString(cartDto)))
			.andExpect(status().isCreated());
		
		//Act & Assert
		this.mockMvc.perform(delete("/rest/cart/" + session.getId()).session(session))
			.andExpect(status().isOk());
		
	}
	
	//@Test
	public void addItem_method_should_return_ok() throws Exception {
		
		//Arrange
		CartItemDto cartItemDto = new CartItemDto(session.getId() + "P1235", "P1235");
		CartDto cartDto = new CartDto(session.getId());
		cartDto.getCartItems().add(cartItemDto);
		
		//Act
		this.mockMvc.perform(put("/rest/cart/add/" + cartItemDto.getProductId()).session(session).contentType(contentType)
			.content(asJsonString(cartDto)))
			.andExpect(status().isOk());
		
		//Assert
		this.mockMvc.perform(get("/rest/cart/" + session.getId()).session(session))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.cartItems[0].product.productId").value("P1235"))
			.andExpect(jsonPath("$.cartItems[0].quantity").value(1));
		
	}
	
	@Test
	public void removeItem_method_should_return_ok() throws Exception {
		
		//Arrange
		CartItemDto cartItemDto = new CartItemDto(session.getId() + "P1235", "P1235");
		CartDto cartDto = new CartDto(session.getId());
		cartDto.getCartItems().add(cartItemDto);
		
		this.mockMvc.perform(put("/rest/cart/add/" + cartItemDto.getProductId()).session(session).contentType(contentType)
			.content(asJsonString(cartDto)))
			.andExpect(status().isOk());
		
		//Act
		this.mockMvc.perform(put("/rest/cart/remove/" + cartItemDto.getProductId()).session(session).contentType(contentType)
				.content(asJsonString(cartDto)))
				.andExpect(status().isOk());
				
		//Assert
		this.mockMvc.perform(get("/rest/cart/" + session.getId()).session(session))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.cartItems").isEmpty());
		
	}
	
	
	/*
     * converts a Java object into JSON representation
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
}

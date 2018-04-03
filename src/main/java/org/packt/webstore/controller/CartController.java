package org.packt.webstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/cart")
public class CartController {
	
	@RequestMapping
	public String get(HttpServletRequest request) {
		
		return "redirect:/cart/" + request.getSession(true).getId();
		
	}

	@GetMapping(value="/{cartId}")
	public String getCart(@PathVariable(value="cartId") String cartId, Model model) {
		
		model.addAttribute("cartId", cartId);
		
		return "cart";
		
	}
	
}

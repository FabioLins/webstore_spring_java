package org.packt.webstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.packt.webstore.domain.Product;
import org.packt.webstore.exception.AddressNotFoundException;
import org.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductIdValidator implements ConstraintValidator<ProductId, String> {

	@Autowired
	private ProductService productService;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Product product;
		
		try {
			product = productService.getProductById(value);
		} catch (AddressNotFoundException e) {
			return true;
		}
		
		if(product != null) {
			return false;
		}
		
		return true;
	}

}

package org.packt.webstore.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class CategoryValidator implements ConstraintValidator<Category, String> {
	
	private List<String> allowedCategories;

	public CategoryValidator() {
		this.allowedCategories = new ArrayList<String>();
		this.allowedCategories.add("Smartphone");
		this.allowedCategories.add("Laptop");
		this.allowedCategories.add("Tablet");
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(allowedCategories.contains(value)) {
			return true;
		}
		
		return false;
	}

}

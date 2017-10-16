package com.craft.reviewable.validator;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.craft.reviewable.domain.Product;

public class ProductValidatorTest {

	@Test
	public void testSupports() {

		boolean result = new ProductValidator().supports(Product.class);
		assert (result);
	}

	@Test
	public void testValidate() {

		Product product = new Product("prod1", "", 3.0, 0, 0, 0, 0, 1);
		Errors errors = new BeanPropertyBindingResult(product, "product");

		ProductValidator validator = new ProductValidator();
		validator.validate(product, errors);

	}
}

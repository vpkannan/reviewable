/**
 * 
 */
package com.craft.reviewable.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.craft.reviewable.domain.Product;

/**
 * @author Vignesh
 *
 */
@Component("beforeCreateProductValidator")
public class ProductValidator implements Validator {
	public static final Logger LOGGER = LoggerFactory.getLogger(com.craft.reviewable.validator.ProductValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		LOGGER.info("Incoming Payload for validator: {}", product);
		if (checkIfEmpty(product.getName())) {
			LOGGER.info("Empty name field detected by validator. Throwing error");
			errors.rejectValue("name", "name.empty", "Product name cannot be empty");
		}

	}

	private boolean checkIfEmpty(String value) {
		return (value == null || value.trim().length() == 0);
	}

}

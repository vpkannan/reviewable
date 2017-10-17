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
import com.craft.reviewable.util.Constants;

/**
 * Spring validator for Product resource
 * 
 * @author Vignesh
 *
 */
@Component("beforeCreateProductValidator")
public class ProductValidator implements Validator {
	public static final Logger LOGGER = LoggerFactory.getLogger(com.craft.reviewable.validator.ProductValidator.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

	/**
	 * Validate incoming request values for Create Product REST API {@inheritDoc}
	 */
	@Override
	public void validate(Object target, Errors errors) {
		LOGGER.info("ProductValidator validating the incoming request for Product");
		Product product = (Product) target;
		LOGGER.debug("Incoming Payload for validator: {}", product);

		// Check: Product ID should not be populated
		if (!checkIfEmpty(product.getId())) {
			LOGGER.info("Bad request. ID should not be passed via REST API");
			errors.rejectValue("id", Constants.RE_PRODUCT_VALIDATION_ID_NOT_EMPTY,
					"Product ID should not be passed while creating new product. It is generated by the system");
		}

		// Check: Product Name should not be empty
		if (checkIfEmpty(product.getName())) {
			LOGGER.info("Bad request. Empty name field detected by validator");
			errors.rejectValue("name", Constants.RE_PRODUCT_VALIDATION_NAME_EMPTY, "Product name cannot be empty");
		}

		// Check: Product Rating should not be populated
		if (product.getAverageRating() != 0.0) {
			LOGGER.info("Bad request. Product rating cannot be populated while creating Product");
			errors.rejectValue("averageRating", Constants.RE_PRODUCT_VALIDATION_RATING_FOUND,
					"Product rating cannot be populated while creating Product. It is computed based on Reviews");
		}

		// Check: Product Rating counts should not be populated
		if (product.getOneStar() != 0 || product.getTwoStar() != 0 || product.getThreeStar() != 0
				|| product.getFourStar() != 0 || product.getFiveStar() != 0) {
			LOGGER.info("Bad request. Product rating counts cannot be populated while creating Product");
			errors.rejectValue("fiveStar", Constants.RE_PRODUCT_VALIDATION_RATING_COUNT_FOUND,
					"Product rating counts cannot be populated while creating Product. It is computed based on Reviews");
		}

	}

	/**
	 * Check if the passed value is empty
	 * 
	 * @param value
	 *            The input value
	 * @return true if value is empty; false if value is not empty
	 */
	private boolean checkIfEmpty(String value) {
		return (value == null || value.trim().length() == 0);
	}

}

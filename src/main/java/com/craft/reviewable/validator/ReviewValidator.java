/**
 * 
 */
package com.craft.reviewable.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.craft.reviewable.domain.Review;
import com.craft.reviewable.util.Constants;

/**
 * @author Vignesh
 *
 */
@Component("beforeCreateReviewValidator")
public class ReviewValidator implements Validator {
	public static final Logger LOGGER = LoggerFactory.getLogger(com.craft.reviewable.validator.ReviewValidator.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Review.class.equals(clazz);
	}

	/**
	 * Validate incoming request values for Create Review REST API {@inheritDoc}
	 */
	@Override
	public void validate(Object target, Errors errors) {
		LOGGER.info("ReviewValidator validating the incoming request for Review");
		Review review = (Review) target;
		LOGGER.debug("Incoming Payload for validator: {}", review);

		// Check: Review ID should not be populated
		if (!checkIfEmpty(review.getId())) {
			LOGGER.info("Bad request. ID should not be passed via REST API");
			errors.rejectValue("id", Constants.RE_REVIEW_VALIDATION_ID_FOUND,
					"Review ID should not be passed while creating new review. It is generated by the system");
		}

		// Check: Product ID is mandatory and should be populated for the review
		if (checkIfEmpty(review.getProductId())) {
			LOGGER.info("Bad request. Product ID not passed in the request. Review must be tied to a Product");
			errors.rejectValue("productId", Constants.RE_REVIEW_VALIDATION_PRODUCT_ID_NOT_FOUND,
					"Product ID is missing in the request. Reviews must be tied to a Product");
		}

		// Check: Review rating should not be empty and between 1 and 5
		if (review.getRating() < 1 || review.getRating() > 5) {
			LOGGER.info("Bad request. Review rating is mandatory and should be between 1 and 5.");
			errors.rejectValue("rating", Constants.RE_REVIEW_VALIDATION_INVALID_RATING,
					"Review rating is a mandatory field and should be between 1 and 5");
		}

		// Check: User name should not be empty
		if (checkIfEmpty(review.getUserName())) {
			LOGGER.info("Bad request. User name cannot be empty for a review.");
			errors.rejectValue("userName", Constants.RE_REVIEW_VALIDATION_EMPTY_USER_NAME,
					"User name is mandatory for a review. Please add name of the reviewer");
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

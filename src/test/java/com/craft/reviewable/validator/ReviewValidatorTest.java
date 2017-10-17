package com.craft.reviewable.validator;

import java.util.Date;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.craft.reviewable.domain.Review;

public class ReviewValidatorTest {

	/**
	 * Test: ReviewValidator supports method
	 */
	@Test
	public void testSupports() {

		boolean result = new ReviewValidator().supports(Review.class);
		assert (result);
	}

	/**
	 * Test: validate Review method
	 */
	@Test
	public void testValidate() {

		Review review = new Review("review1", "", 8, "Good product", "Excellent design", "", new Date());
		Errors errors = new BeanPropertyBindingResult(review, "review");

		ReviewValidator validator = new ReviewValidator();
		validator.validate(review, errors);

	}
}

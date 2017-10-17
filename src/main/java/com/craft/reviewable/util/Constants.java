package com.craft.reviewable.util;

/**
 * Class to store all Constants like error codes, messages etc.
 * 
 * @author Vignesh
 *
 */
public class Constants {

	private Constants() {
		super();
	}

	// Error code: Product not found in database
	public static final String RE_PRODUCT_NOT_FOUND = "R-4401";

	// Error code: No reviews found in the database for the given product
	public static final String RE_REVIEW_NOT_FOUND = "R-4402";

	// Error codes: Product related validation errors
	public static final String RE_PRODUCT_VALIDATION_ID_NOT_EMPTY = "R-4001";
	public static final String RE_PRODUCT_VALIDATION_NAME_EMPTY = "R-4002";
	public static final String RE_PRODUCT_VALIDATION_RATING_FOUND = "R-4005";
	public static final String RE_PRODUCT_VALIDATION_RATING_COUNT_FOUND = "R-4006";

	// Error codes: Review related validation errors
	public static final String RE_REVIEW_VALIDATION_ID_FOUND = "R-4101";
	public static final String RE_REVIEW_VALIDATION_PRODUCT_ID_NOT_FOUND = "R-4102";
	public static final String RE_REVIEW_VALIDATION_INVALID_RATING = "R-4103";
	public static final String RE_REVIEW_VALIDATION_EMPTY_USER_NAME = "R-4104";

}

package com.craft.reviewable.exception;

import com.craft.reviewable.domain.error.ReviewableError;

public class ReviewableException extends Exception {

	private static final long serialVersionUID = 1L;
	private final ReviewableError error;

	public ReviewableException(ReviewableError error) {
		super();
		this.error = error;
	}

	public ReviewableError getError() {
		return error;
	}

}

/**
 * 
 */
package com.craft.reviewable.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.craft.reviewable.domain.Review;
import com.craft.reviewable.exception.ReviewableException;

/**
 * @author Vignesh
 *
 */

@Component
public interface ReviewService {
	/**
	 * Retrieve all product reviews for a given Product based on the Product ID
	 * 
	 * @param productId
	 *            The unique identifier for the Product
	 * @param pageable
	 *            The pagination attributes
	 * @return A Page of Review objects which for the given Product
	 * @throws ReviewableException
	 */
	public Page<Review> listProductReviews(String productId, Pageable pageable) throws ReviewableException;

	/**
	 * Add new Review for a Product based on the Product ID
	 * 
	 * @param review
	 *            The Review object
	 * @return The created Review object along with the auto-generated ID
	 * @throws ReviewableException
	 */
	public Review addReview(Review review) throws ReviewableException;

}

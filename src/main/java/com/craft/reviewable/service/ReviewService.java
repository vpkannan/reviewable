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

	public Page<Review> listProductReviews(String productId, Pageable pageable) throws ReviewableException;

	public Review addReview(Review review) throws ReviewableException;

}

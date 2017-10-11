/**
 * 
 */
package com.vignesh.reviewable.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vignesh.reviewable.domain.Review;

/**
 * @author Vignesh
 *
 */

@Component
public interface ReviewService {

	public List<Review> getAllReviews(long productId);

	public Review getReviewById(long id);

	public Review createReview(Review review);

}

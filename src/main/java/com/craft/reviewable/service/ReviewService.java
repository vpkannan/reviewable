/**
 * 
 */
package com.craft.reviewable.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.craft.reviewable.domain.Review;
import com.craft.reviewable.exception.ReviewableException;

/**
 * @author Vignesh
 *
 */

@Component
public interface ReviewService {

	public List<Review> listProductReviews(String productId) throws ReviewableException;

	public String addReview(Review review) throws ReviewableException;

}

package com.craft.reviewable.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.craft.reviewable.domain.Product;
import com.craft.reviewable.domain.Review;
import com.craft.reviewable.domain.error.ReviewableError;
import com.craft.reviewable.exception.ReviewableException;
import com.craft.reviewable.repository.ProductRepository;
import com.craft.reviewable.repository.ReviewRepository;
import com.craft.reviewable.service.ReviewService;

@Component
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public Page<Review> listProductReviews(String productId, Pageable pageable) throws ReviewableException {
		Page<Review> reviewsPage = reviewRepository.findByProductId(productId, pageable);

		if (reviewsPage == null || reviewsPage.getContent().size() == 0) {
			// Product not found
			ReviewableError error = new ReviewableError();
			error.setErrorCode("R-4001");
			error.setErrorDescription("Product ID entered is invalid");
			ReviewableException ex = new ReviewableException(error);
			throw ex;
		}

		return reviewsPage;
	}

	@Override
	public Review addReview(Review review) throws ReviewableException {

		Product product = productRepository.findOne(review.getProductId());

		if (product == null) {
			// Product not found
			ReviewableError error = new ReviewableError();
			error.setErrorCode("R-4001");
			error.setErrorDescription("Product ID entered is invalid");
			ReviewableException ex = new ReviewableException(error);
			throw ex;
		}

		if (review.getDate() == null)
			review.setDate(new Date());

		Review addedReview = reviewRepository.save(review);

		product.setAverageRating(product.calculateNewAverageRating(addedReview.getRating()));

		productRepository.save(product);

		return addedReview;
	}

}

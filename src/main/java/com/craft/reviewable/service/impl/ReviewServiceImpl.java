package com.craft.reviewable.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.craft.reviewable.domain.Product;
import com.craft.reviewable.domain.Review;
import com.craft.reviewable.domain.ReviewableError;
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
	public List<Review> listProductReviews(String productId) throws ReviewableException {
		Product product = productRepository.findOne(productId);

		if (product == null) {
			// Product not found
			ReviewableError error = new ReviewableError();
			error.setErrorCode("R-4001");
			error.setErrorDescription("Product ID entered is invalid");
			ReviewableException ex = new ReviewableException(error);
			throw ex;
		}

		return product.getReviews();
	}
	

	@Override
	public String addReview(Review review) throws ReviewableException {
		Product product = productRepository.findOne(review.getProduct().getId());

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

		product.setReviews(product.addReview(review));
		product.setAverageRating(product.calculateNewAverageRating(review.getRating()));

		productRepository.save(product);

		return "Successfully added review";
	}

}

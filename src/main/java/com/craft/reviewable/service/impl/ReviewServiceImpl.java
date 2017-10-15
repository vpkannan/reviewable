package com.craft.reviewable.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	public static final Logger LOGGER = LoggerFactory
			.getLogger(com.craft.reviewable.service.impl.ReviewServiceImpl.class);

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	ProductRepository productRepository;

	/**
	 * Constructor to initiate the repositories. Used in unit tests.
	 * 
	 * @param reviewRepository
	 *            The review repository
	 * @param productRepository
	 *            The product repository
	 */
	public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository) {
		this.reviewRepository = reviewRepository;
		this.productRepository = productRepository;
	}

	/**
	 * Get all Reviews for a Product
	 *
	 * {@inheritDoc}
	 */
	@Override
	public Page<Review> listProductReviews(String productId, Pageable pageable) throws ReviewableException {

		LOGGER.info("Fetching all reviews for a given product ID from DB");
		LOGGER.debug("Fetching all reviews for product: {}", productId);

		Page<Review> reviewsPage = reviewRepository.findByProductId(productId, pageable);

		if (reviewsPage == null || reviewsPage.getContent().isEmpty()) {
			LOGGER.info("Product with the given product ID is not found in DB");
			LOGGER.info("Throwing a customized error message");
			ReviewableError error = new ReviewableError();
			error.setErrorCode("R-4002");
			error.setErrorDescription("Could not fetch reviews for the product. Invalid data");
			ReviewableException ex = new ReviewableException(error);
			LOGGER.debug("Exception stacktrace: {}", ex);
			throw ex;
		}

		return reviewsPage;
	}

	/**
	 * Add a new Review for a Product
	 *
	 * {@inheritDoc}
	 */
	@Override
	public Review addReview(Review review) throws ReviewableException {

		LOGGER.info("Adding a new review for the given product");
		LOGGER.debug("Review details: {}", review);

		Product product = productRepository.findOne(review.getProductId());

		if (product == null) {
			LOGGER.info("Product with the given product ID is not found in DB. Review will not be added");
			LOGGER.info("Throwing a customized error message");
			ReviewableError error = new ReviewableError();
			error.setErrorCode("R-4001");
			error.setErrorDescription("Product ID entered is invalid. Review not added.");
			ReviewableException ex = new ReviewableException(error);
			LOGGER.debug("Exception stacktrace: {}", ex);
			throw ex;
		}

		if (review.getDate() == null) {
			LOGGER.info("Date not set in the review. Setting date to current system date");
			review.setDate(new Date());
		}

		Review addedReview = reviewRepository.save(review);
		LOGGER.info("Review saved to the DB");

		LOGGER.info("Updating the average rating of the product based on the latest review");
		LOGGER.debug("Product details before new rating calculation: {}", product);
		product.setAverageRating(product.calculateNewAverageRating(addedReview.getRating()));
		Product updatedProduct = productRepository.save(product);

		LOGGER.info("New rating details updated to the product");
		LOGGER.debug("Product details with new rating calculation: {}", updatedProduct);
		return addedReview;
	}

}

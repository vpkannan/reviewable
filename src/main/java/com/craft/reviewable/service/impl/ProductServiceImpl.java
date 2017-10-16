package com.craft.reviewable.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.craft.reviewable.domain.Product;
import com.craft.reviewable.domain.error.ReviewableError;
import com.craft.reviewable.exception.ReviewableException;
import com.craft.reviewable.repository.ProductRepository;
import com.craft.reviewable.service.ProductService;

/**
 * @author Vignesh
 *
 */
@Component
public class ProductServiceImpl implements ProductService {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(com.craft.reviewable.service.impl.ProductServiceImpl.class);

	@Autowired
	ProductRepository productRepository;

	/**
	 * Constructor initializing the repository. Used for unit tests
	 * 
	 * @param productRepository
	 *            The product repository
	 */
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * Get all Products
	 *
	 * {@inheritDoc}
	 * 
	 * @throws ReviewableException
	 */
	@Override
	public List<Product> getAllProducts() throws ReviewableException {
		LOGGER.info("Fetching all products from DB");
		List<Product> products = (List<Product>) productRepository.findAll();
		if (products.isEmpty()) {
			LOGGER.info("No Products found in DB");
			LOGGER.info("Returning a customized error response");
			ReviewableError error = new ReviewableError();
			error.setErrorCode("R-4004");
			error.setErrorDescription("No Products present in the system");
			ReviewableException ex = new ReviewableException(error);
			LOGGER.debug("Exception stacktrace: {}", ex);
			throw ex;
		}
		return products;
	}

	/**
	 * Get Product by ID
	 *
	 * {@inheritDoc}
	 */
	@Override
	public Product getProductById(String id) throws ReviewableException {
		LOGGER.info("Fetching product details from DB based on ID provided");
		Product product = productRepository.findOne(id);
		if (product == null) {
			LOGGER.info("Product with the given product ID is not found in DB");
			LOGGER.info("Throwing a customized error message");
			ReviewableError error = new ReviewableError();
			error.setErrorCode("R-4001");
			error.setErrorDescription("Could not fetch product details. Product ID entered is invalid");
			ReviewableException ex = new ReviewableException(error);
			LOGGER.debug("Exception stacktrace: {}", ex);
			throw ex;
		}
		LOGGER.info("Obtained a product matching the given product ID.");
		LOGGER.debug("Retrieved Product details: {}", product);
		return product;
	}

	/**
	 * Create Product
	 *
	 * {@inheritDoc}
	 */
	@Override
	public Product createProduct(Product product) {
		LOGGER.info("Attempting to add new product to the DB");
		Product createdProduct = productRepository.save(product);
		LOGGER.debug("Product created in the DB. Product details: {}", createdProduct);
		return createdProduct;
	}

}

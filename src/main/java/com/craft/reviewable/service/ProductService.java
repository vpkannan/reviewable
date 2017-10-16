package com.craft.reviewable.service;

import java.util.List;

import com.craft.reviewable.domain.Product;
import com.craft.reviewable.exception.ReviewableException;

public interface ProductService {

	/**
	 * Retrieve all products from the database
	 * 
	 * @return List of all Product entries from the DB
	 * @throws ReviewableException
	 */
	public List<Product> getAllProducts() throws ReviewableException;

	/**
	 * Retrieve product details based on Product ID
	 * 
	 * @param id
	 *            Unique identifier for the Product entity
	 * @return The Product entity retrieved from the DB
	 * @throws ReviewableException
	 */
	public Product getProductById(String id) throws ReviewableException;

	/**
	 * Create a new Product and persist it in the database
	 * 
	 * @param product
	 *            The new Product object
	 * @return The created product along with the auto-generated ID
	 */
	public Product createProduct(Product product);
}

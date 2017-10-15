package com.craft.reviewable.service;

import java.util.List;

import com.craft.reviewable.domain.Product;
import com.craft.reviewable.exception.ReviewableException;

public interface ProductService {

	public List<Product> getAllProducts();

	public Product getProductById(String id) throws ReviewableException;

	public Product createProduct(Product product);
}

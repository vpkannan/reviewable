package com.craft.reviewable.service;

import java.util.List;

import com.craft.reviewable.domain.Product;

public interface ProductService {

	public List<Product> getAllProducts();

	public Product getProductById(String id);

	public Product createProduct(Product product);
}

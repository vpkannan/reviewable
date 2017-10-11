package com.vignesh.reviewable.service;

import java.util.List;

import com.vignesh.reviewable.domain.Product;

public interface ProductService {

	public List<Product> getAllProducts();

	public Product getProductById(long id);

	public Product createProduct(Product product);
}

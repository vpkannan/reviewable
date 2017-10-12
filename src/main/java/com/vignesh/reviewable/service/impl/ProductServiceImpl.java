package com.vignesh.reviewable.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vignesh.reviewable.domain.Product;
import com.vignesh.reviewable.repository.ProductRepository;
import com.vignesh.reviewable.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(String id) {
		return productRepository.findOne(id);
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

}

package com.vignesh.reviewable.service;

import java.util.List;

import com.vignesh.reviewable.domain.Product;

public interface ProductService {

	public List<Product> getAllProjects();

	public Product getProjectById(long id);

	public Product createProject(Product project);
}

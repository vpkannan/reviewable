/**
 * 
 */
package com.vignesh.reviewable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vignesh.reviewable.domain.Product;
import com.vignesh.reviewable.service.ProductService;

/**
 * @author Vignesh
 *
 */

@RestController
@RequestMapping("projects")
public class ProductController {

	@Autowired
	private ProductService projectService;

	public ProductController(ProductService projectService) {
		this.projectService = projectService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Product> getAllProjects() {
		// TODO: call project service
		return null;
	}

}

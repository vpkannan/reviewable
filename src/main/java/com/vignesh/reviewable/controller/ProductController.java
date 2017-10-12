/**
 * 
 */
package com.vignesh.reviewable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("products")
public class ProductController {

	@Autowired
	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	// @RequestMapping(method = RequestMethod.GET)
	// public List<Product> getAllProducts() {
	// // TODO: call product service
	// return null;
	// }

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> ping() {
		return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Product createProduct(Product product) {
		return productService.createProduct(product);
	}

}

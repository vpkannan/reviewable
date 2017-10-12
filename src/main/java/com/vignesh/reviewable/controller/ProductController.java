/**
 * 
 */
package com.vignesh.reviewable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Product getProductById(@PathVariable String id) {
		return productService.getProductById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> ping() {
		return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Product createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}

}

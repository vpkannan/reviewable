/**
 * 
 */
package com.craft.reviewable.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.craft.reviewable.domain.Product;
import com.craft.reviewable.service.ProductService;
import com.craft.reviewable.validator.ProductValidator;

/**
 * @author Vignesh
 *
 */

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	ProductValidator productValidator;

	public static final Logger LOGGER = LoggerFactory
			.getLogger(com.craft.reviewable.controller.ProductController.class);

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(productValidator);
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
	public Product createProduct(@Valid @RequestBody Product product) {
		LOGGER.info("Incoming Product payload: {}", product);
		return productService.createProduct(product);
	}

}

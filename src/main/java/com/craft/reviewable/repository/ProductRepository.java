/**
 * 
 */
package com.craft.reviewable.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.craft.reviewable.domain.Product;

/**
 * Repository for Product entity
 * 
 * @author Vignesh
 *
 */
@Component
public interface ProductRepository extends CrudRepository<Product, String> {

}

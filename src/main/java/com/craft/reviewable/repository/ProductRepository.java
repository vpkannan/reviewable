/**
 * 
 */
package com.craft.reviewable.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.craft.reviewable.domain.Product;

/**
 * @author Vignesh
 *
 */
@Component
public interface ProductRepository extends MongoRepository<Product, String> {

}

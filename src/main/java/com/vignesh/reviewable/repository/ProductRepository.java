/**
 * 
 */
package com.vignesh.reviewable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vignesh.reviewable.domain.Product;

/**
 * @author Vignesh
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}

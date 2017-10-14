/**
 * 
 */
package com.craft.reviewable.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.craft.reviewable.domain.Review;

/**
 * @author Vignesh
 *
 */
@Component
public interface ReviewRepository extends CrudRepository<Review, String> {

}

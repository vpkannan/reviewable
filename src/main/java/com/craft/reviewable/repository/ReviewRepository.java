/**
 * 
 */
package com.craft.reviewable.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.craft.reviewable.domain.Review;

/**
 * @author Vignesh
 *
 */
@Component
public interface ReviewRepository extends PagingAndSortingRepository<Review, String> {

	Page<Review> findByProductId(String productId, Pageable pageable);

}

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

	/**
	 * Find Reviews in the DB based on the Product ID
	 * 
	 * @param productId
	 *            The unique Product ID representing the Product
	 * @param pageable
	 *            The pagination attributes
	 * @return Page of Review objects based on the Product ID and pagination
	 *         criteria
	 */
	Page<Review> findByProductId(String productId, Pageable pageable);

}

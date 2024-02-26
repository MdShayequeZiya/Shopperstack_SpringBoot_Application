package com.ff.shopperstack.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.shopperstack.entity.Product;
import com.ff.shopperstack.entity.Review;
import com.ff.shopperstack.repository.ReviewRepository;

@Repository
public class ReviewDao {
	
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	
	public Review saveReview(Review review) {
		
		return reviewRepository.save(review);
		
	}
	
	public List<Review> getReviewByProduct(Product product){
		
		return reviewRepository.findAllByProduct(product);
	}

}

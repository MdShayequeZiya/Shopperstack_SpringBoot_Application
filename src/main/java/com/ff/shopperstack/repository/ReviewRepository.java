package com.ff.shopperstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.shopperstack.entity.Product;
import com.ff.shopperstack.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
	
	List<Review> findAllByProduct(Product product);

}

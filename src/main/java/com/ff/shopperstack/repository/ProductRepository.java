package com.ff.shopperstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ff.shopperstack.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public Product findById(int productId);
	
	public List<Product> findByIdIn(List<Integer> productIds); 

}

package com.ff.shopperstack.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.shopperstack.entity.Product;
import com.ff.shopperstack.repository.ProductRepository;

@Repository
public class ProductDao {
	
	
	@Autowired
	private ProductRepository productRepository;
	
	
	public Product saveProduct(Product product) {
		
		return productRepository.save(product);
		
	}
	
	public Product findById(int id) {
		return productRepository.findById(id);
	}
	
	
	public void deleteProduct(int productId) {
		
		productRepository.deleteById(productId);
	
	}
	
	public List<Product> findAllProducts(){
		List<Product> findAll = productRepository.findAll();
		return findAll;
	}

	
	public List<Product> findAllProductsByIds(List<Integer> productIds){
		
		return productRepository.findByIdIn(productIds);
	}
}

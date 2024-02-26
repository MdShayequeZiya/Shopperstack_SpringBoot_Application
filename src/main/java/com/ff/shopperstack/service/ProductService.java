package com.ff.shopperstack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.shopperstack.dao.ProductDao;
import com.ff.shopperstack.dao.ReviewDao;
import com.ff.shopperstack.dao.UserDao;
import com.ff.shopperstack.dto.ResponseStructure;
import com.ff.shopperstack.entity.Product;
import com.ff.shopperstack.entity.Review;
import com.ff.shopperstack.entity.Shopper;
import com.ff.shopperstack.exception.NotAuthorised;

@Service
public class ProductService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ReviewDao reviewDao;
	
	
	public ResponseEntity<ResponseStructure<String>> saveProduct(int id, Product product) {
		
		String role = userDao.findRoleById(id);
		if(role!=null && role.equalsIgnoreCase("merchant")) {
			
			Shopper shopper = userDao.findById(id);
			product.setShopper(shopper);
			productDao.saveProduct(product);
			
			ResponseStructure<String> responseStructure = new ResponseStructure<String>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
			responseStructure.setData("Product saved successfully!");
			
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.CREATED);
		}
		
		throw new NotAuthorised();	
	}
	
	
	public ResponseEntity<ResponseStructure<String>> updateProduct(int userId, int productId, Product newProduct){
		
		String role = userDao.findRoleById(userId);
		if(role!=null && role.equalsIgnoreCase("merchant")) {
			
			Shopper shopper = userDao.findById(userId);
			Product oldProduct = productDao.findById(productId);
			if(oldProduct != null && oldProduct.getShopper() == shopper) {
				
				oldProduct.setProductName(newProduct.getProductName());
				oldProduct.setProductPrice(newProduct.getProductPrice());
				
				productDao.saveProduct(oldProduct);
				ResponseStructure<String> responseStructure = new ResponseStructure<String>();
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("Success");
				responseStructure.setData("Product Updated successfully!");
				
				return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);	
				
			}else {
				throw new NotAuthorised();
			}
		}else {
			throw new NotAuthorised();
		}
		
	}
	
	
	public ResponseEntity<ResponseStructure<String>> deleteProduct(int userId, int productId){

		String role = userDao.findRoleById(userId);
		if(role!=null && role.equalsIgnoreCase("merchant")) {
			
			Shopper shopper = userDao.findById(userId);
			Product product = productDao.findById(productId);
			if(product != null && product.getShopper() == shopper) {
				
				productDao.deleteProduct(productId);
				
				ResponseStructure<String> responseStructure = new ResponseStructure<String>();
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("Success");
				responseStructure.setData("Product Deleted successfully!");
				
				return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
				
				
			}
			throw new NotAuthorised();
		}
		
		throw new NotAuthorised();
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts(int userId){
		
		String role = userDao.findRoleById(userId);
		if(role!=null && role.equalsIgnoreCase("customer")) {
			
			List<Product> products = productDao.findAllProducts();
			
			ResponseStructure<List<Product>> rs = new ResponseStructure<List<Product>>();
			
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Success");
			rs.setData(products);
			
			return new ResponseEntity<ResponseStructure<List<Product>>>(rs, HttpStatus.OK);
			
			
		}else {
			throw new NotAuthorised();
		}
		
	}
	
	public ResponseEntity<ResponseStructure<String>> saveReview(int userId, int productId, Review review){
		
		String role = userDao.findRoleById(userId);
		if(role!=null && role.equalsIgnoreCase("customer")) {
			
			Product product = productDao.findById(productId);
			
			if(product != null) {
				review.setProduct(product);
				
				reviewDao.saveReview(review);
				
				ResponseStructure<String> rs = new ResponseStructure<String>();
				rs.setStatusCode(HttpStatus.OK.value());
				rs.setMessage("Success");
				rs.setData("Review added successfully.");
				
				return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
			}
			
			throw new NotAuthorised("Product Id is not valid");
			
		}
		
		throw new NotAuthorised();
	}
	
	
	public ResponseEntity<ResponseStructure<List<Review>>> getAllReviewOfTheProduct(int userId, int productId){
		
		
		String role = userDao.findRoleById(userId);
		if(role!=null && role.equalsIgnoreCase("merchant")) {
			
			Shopper shopper = userDao.findById(userId);
			Product product = productDao.findById(productId);
			if(product != null && product.getShopper() == shopper) {
				
				List<Review> reviews = reviewDao.getReviewByProduct(product);
			
				ResponseStructure<List<Review>> rs = new ResponseStructure<>();
				rs.setStatusCode(HttpStatus.OK.value());
				rs.setMessage("Success");
				rs.setData(reviews);
				
				return new ResponseEntity<ResponseStructure<List<Review>>>(rs, HttpStatus.OK);
			
			}
			throw new NotAuthorised("Product you are trying to review doesnot belong to the shopperId");
		}
		
		
		throw new NotAuthorised();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
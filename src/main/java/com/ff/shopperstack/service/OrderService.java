package com.ff.shopperstack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.shopperstack.dao.OrderDao;
import com.ff.shopperstack.dao.ProductDao;
import com.ff.shopperstack.dao.UserDao;
import com.ff.shopperstack.dto.ResponseStructure;
import com.ff.shopperstack.entity.Product;
import com.ff.shopperstack.entity.Shopper;
import com.ff.shopperstack.entity.UserOrder;
import com.ff.shopperstack.exception.NotAuthorised;
import com.ff.shopperstack.util.Cart;

@Service
public class OrderService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private OrderDao orderDao;
	
	
	public ResponseEntity<ResponseStructure<String>> createAnOrder(int userId, Cart cart){
		
		
		String role = userDao.findRoleById(userId);
		if(role!=null && role.equalsIgnoreCase("customer")) {
			
			Shopper shopper = userDao.findById(userId);
			
			List<Product> findAllProductsByIds = productDao.findAllProductsByIds(cart.getProducts());
			
			if(!findAllProductsByIds.isEmpty()) {
				
				UserOrder order = new UserOrder();
				order.setOrderType(cart.getType());
				order.setProducts(findAllProductsByIds);
				order.setShopper(shopper);
				
				orderDao.saveOrder(order);
				
				ResponseStructure<String> responseStructure = new ResponseStructure<String>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Success");
				responseStructure.setData("Order saved successfully!");
				
				return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.CREATED);
				
			}
			throw new NotAuthorised("No products found with the ids listed!");
			
		}
			
		
		throw new NotAuthorised();
	}
	
	
	public ResponseEntity<ResponseStructure<List<UserOrder>>> getAllOrders(int userId){
		String role = userDao.findRoleById(userId);
		if(role!=null && role.equalsIgnoreCase("customer")) {
			
			List<UserOrder> allOrders = orderDao.getAllOrders(userId);
			
			ResponseStructure<List<UserOrder>> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Success");
			rs.setData(allOrders);
			
			return new ResponseEntity<ResponseStructure<List<UserOrder>>>(rs, HttpStatus.OK);
			
		}
		
		throw new NotAuthorised();
	}

}

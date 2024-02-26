package com.ff.shopperstack.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.shopperstack.entity.UserOrder;
import com.ff.shopperstack.repository.OrderRepository;


@Repository
public class OrderDao {
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	public UserOrder saveOrder(UserOrder order) {
		
		return orderRepository.save(order);
	}
	
	public List<UserOrder> getAllOrders(int userId){
		return orderRepository.findByShopperId(userId);
	}

}

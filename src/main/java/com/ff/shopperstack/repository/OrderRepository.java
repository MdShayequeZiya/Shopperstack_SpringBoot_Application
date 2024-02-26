package com.ff.shopperstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ff.shopperstack.entity.UserOrder;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Integer> {
	
	
	public List<UserOrder> findByShopperId(int userId);

}

package com.ff.shopperstack.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.shopperstack.entity.Shopper;
import com.ff.shopperstack.repository.UserRepository;

@Repository
public class UserDao {
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	public Shopper login(String email, String password) {
		
		return userRepository.findByEmailAndPassword(email, password);
		
	}
	
	
	public String findRoleById(int id) {
		return userRepository.findRoleById(id);
	}
	
	public Shopper findById(int id) {
		return userRepository.findById(id);
	}

}

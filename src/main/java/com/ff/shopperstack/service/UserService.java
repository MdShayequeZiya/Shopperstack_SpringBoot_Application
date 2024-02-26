package com.ff.shopperstack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.shopperstack.dao.UserDao;
import com.ff.shopperstack.dto.ResponseStructure;
import com.ff.shopperstack.entity.Shopper;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<Shopper>> login(String email, String password) {
		
		Shopper loginUser = userDao.login(email, password);
		
		
		ResponseStructure<Shopper> responseStructure = new ResponseStructure<Shopper>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Successfull");
		responseStructure.setData(loginUser);
		
		
		return new ResponseEntity<ResponseStructure<Shopper>>(responseStructure, HttpStatus.OK);
		
	}

}

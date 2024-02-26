package com.ff.shopperstack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ff.shopperstack.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> catchNotAuthorised(NotAuthorised exception){
		
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
		rs.setMessage("BAD REQUEST");
		rs.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.BAD_REQUEST);
		
	}

}

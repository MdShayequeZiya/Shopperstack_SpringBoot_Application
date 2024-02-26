package com.ff.shopperstack.util;

import java.util.List;

import lombok.Data;

@Data
public class Cart {
	
	private List<Integer> products;
	
	private String type;
}

package com.ff.shopperstack.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "productGenerator")
	@SequenceGenerator(name="productGenerator", initialValue = 400, allocationSize = 1, sequenceName = "productGenerator")
	private int id;
	
	private String productName;
	
	private double productPrice;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Review> reviews;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "products")
	private List<UserOrder> orders;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Shopper shopper;
	

}

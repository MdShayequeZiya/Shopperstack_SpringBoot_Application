package com.ff.shopperstack.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Shopper {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userGenerator")
	@SequenceGenerator(name="userGenerator", initialValue = 200, allocationSize = 1, sequenceName = "userGenerator")
	private int id;
	
	private String userName;
	
	private String userAddress;
	
	private String role;
	
	@Column(unique = true)
	@Email
	private String email;
	
	@JsonIgnore
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "shopper", cascade = CascadeType.ALL)
	private List<UserOrder> orders;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "shopper", cascade = CascadeType.ALL)
	private List<Product> products;

}

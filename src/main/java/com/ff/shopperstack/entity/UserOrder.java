package com.ff.shopperstack.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "orders")
public class UserOrder {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "orderGenerator")
	@SequenceGenerator(name="orderGenerator", initialValue = 300, allocationSize = 1, sequenceName = "orderGenerator")
	private int id;
	
	@CreationTimestamp
	private LocalDateTime orderDate;
	
	private String orderType;
	
	@ManyToMany
	@JoinTable(inverseJoinColumns = @JoinColumn, joinColumns = @JoinColumn)
	private List<Product> products;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Shopper shopper;

}

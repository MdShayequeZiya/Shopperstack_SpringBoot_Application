package com.ff.shopperstack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "reviewGenerator")
	@SequenceGenerator(name="reviewGenerator", initialValue = 500, allocationSize = 1, sequenceName = "reviewGenerator")
	private int id;
	
	private double rating;
	private String review;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Product product;

}

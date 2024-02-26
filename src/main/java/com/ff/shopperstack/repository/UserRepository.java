package com.ff.shopperstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ff.shopperstack.entity.Shopper;

@Repository
public interface UserRepository extends JpaRepository<Shopper, Integer> {
	
	
	public Shopper findByEmailAndPassword(String email, String password);
	
	@Query("SELECT u.role from Shopper u where u.id = :id")
	public String findRoleById(int id);
	
	
	public Shopper findById(int id);


}

package com.ff.shopperstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ff.shopperstack.dto.ResponseStructure;
import com.ff.shopperstack.entity.Product;
import com.ff.shopperstack.entity.Review;
import com.ff.shopperstack.entity.Shopper;
import com.ff.shopperstack.entity.UserOrder;
import com.ff.shopperstack.service.OrderService;
import com.ff.shopperstack.service.ProductService;
import com.ff.shopperstack.service.UserService;
import com.ff.shopperstack.util.Cart;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/shop")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	
	@Operation(description = "RestApi to login to the shopperstack", summary = "Login to the shopperstack")
	@ApiResponses( value = @ApiResponse(description = "Success", responseCode = "200"))
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<Shopper>> login(@Valid @RequestParam String email, @RequestParam String password){
		
		return userService.login(email, password);
	}
	
	
	@Operation(description = "RestApi to save products to the shopperstack by merchant", summary = "Save product")
	@ApiResponses( value = @ApiResponse(description = "Save products to the shopperstack by merchant", responseCode = "201"))
	@PostMapping("/{id}/products")
	public ResponseEntity<ResponseStructure<String>> saveProducts( @PathVariable int id, @RequestBody Product product){
		
		return productService.saveProduct(id, product);
		
	}
	
	@Operation(description = "Saves the data with existing id",summary = "Saves the Updated Product Data")
	@ApiResponses( value = @ApiResponse(description = "OK", responseCode = "200"))
	@PutMapping("/{userId}/products/{productId}")
	public ResponseEntity<ResponseStructure<String>> updateProduct(@PathVariable int userId, @PathVariable int productId, @RequestBody Product product){
		
		return productService.updateProduct(userId, productId, product); 
		
	}
	
	@Operation(description = "will delete the product based on specific product id",summary = "Delete the product based on id")
	@ApiResponses( value = @ApiResponse(description = "OK", responseCode = "201"))
	@DeleteMapping("/{userId}/products/{productId}")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int userId, @PathVariable int productId){
		
		return productService.deleteProduct(userId, productId);
	}
	
	@Operation(description = "will find return all products of specific merchant",summary = "Return all the product of specific merchant")
	@ApiResponses( value = @ApiResponse(description = "all the products data is returned", responseCode = "200"))
	@GetMapping("/{userId}/products")
	public ResponseEntity<ResponseStructure<List<Product>>> getAllTheProducts(@PathVariable int userId){
		
		return productService.getAllProducts(userId);
		
	}
	
	@Operation(description = "saves the review data to corresponding product of corresponding customer ID",summary = "Add review to the product")
	@ApiResponses( value = @ApiResponse(description = "OK", responseCode = "201"))
	@PostMapping("/{userId}/products/{productId}")
	public ResponseEntity<ResponseStructure<String>> saveAReview(@PathVariable int userId, @PathVariable int productId, @RequestBody Review review){
		
		return productService.saveReview(userId, productId, review);
	}
	
	
	@Operation(description = "For specified user Id order info is saved in DataBase ",summary = "Place the for specified customer")
	@ApiResponses( value = @ApiResponse(description = "Created", responseCode = "201"))
	@PostMapping("/{userId}/orders")
	public ResponseEntity<ResponseStructure<String>> saveOrder(@PathVariable int userId, @RequestBody Cart cart){
		return orderService.createAnOrder(userId, cart);
	}
	
	@Operation(description = "Specific User Order Retirved from DB",summary = "Display Order history")
	@ApiResponses( value = @ApiResponse(description = "OK", responseCode = "200"))
	@GetMapping("/orders")
	public ResponseEntity<ResponseStructure<List<UserOrder>>> getAllOrders(@RequestParam int userId){
		return orderService.getAllOrders(userId);
	}
	
	@Operation(description = "will find and return the reviews of correcponding product ID created by correcponding Merchant Id ",summary = "Get All reviews of the product")
	@ApiResponses( value = @ApiResponse(description = "OK", responseCode = "200"))
	@GetMapping("{userId}/review/{productId}")
	public ResponseEntity<ResponseStructure<List<Review>>> getAllReviewOfProduct(@PathVariable int userId, @PathVariable int productId){
		
		return productService.getAllReviewOfTheProduct(userId, productId);
	}

}

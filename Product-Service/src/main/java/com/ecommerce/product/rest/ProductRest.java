package com.ecommerce.product.rest;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.wrapper.ProductWrapper;



@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:4200")
public interface ProductRest {
	
	@PostMapping("/add")
	ResponseEntity<String> addProduct(@RequestBody(required = true) Map<String, String> requestParam,@RequestHeader("LoggedInUser") String username);
	
	@GetMapping("/get")
	ResponseEntity<List<ProductWrapper>> getAllProduct();
	
	@GetMapping("/gets")
	public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size);
	
	//only for admin
	@PostMapping("/update")
	ResponseEntity<String> updateProduct(@RequestBody(required = true) Map<String, String> requestParam);
	
	//only for admin
	@PostMapping("/delete")
	ResponseEntity<String> deleteProduct(@PathVariable Integer id);
	
	//only for admin
	@PostMapping("/updatestatus")
	ResponseEntity<String> updateProductStatus(@RequestBody(required = true) Map<String, String> requestMap);
	
	@GetMapping("/byCategoryId/{id}")
	ResponseEntity<List<ProductWrapper>> getProductByCategory(@PathVariable Integer id);
	@GetMapping("/byProductId/{id}")
	ResponseEntity<ProductWrapper> getProductById(@PathVariable Integer id);
    
}

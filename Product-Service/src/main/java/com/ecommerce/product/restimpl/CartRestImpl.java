package com.ecommerce.product.restimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product.rest.CartRest;
import com.ecommerce.product.serviceimpl.CartService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class CartRestImpl implements CartRest {
	
	@Autowired
	private CartService cartService;

	@Override
	public ResponseEntity<?> addToCart(Map<String, String> requestParam) {
		return cartService.addToCart(requestParam);
	}

	@Override
	public ResponseEntity<?> getCartItems(String email) {
		  
		return cartService.getAllCarts(email);
	}

	@Override
	public ResponseEntity<?> deleteCart(Map<String, String> requestParam) {
		
		return cartService.deleteCartItem(requestParam);
	}

}

package com.ecommerce.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CartItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;

	private long productId;
	private String email;
	private String name;
	private double price;
	private int quantity=1;
	private String imageUrl;
}

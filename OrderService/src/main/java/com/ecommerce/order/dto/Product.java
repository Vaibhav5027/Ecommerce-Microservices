package com.ecommerce.order.dto;

import lombok.Data;

@Data
public class Product {
  
    private String name;  
	private String description;
	private String status;
	private Integer price;
	private String categoryName;
}

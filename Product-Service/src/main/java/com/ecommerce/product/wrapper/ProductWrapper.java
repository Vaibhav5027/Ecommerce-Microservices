package com.ecommerce.product.wrapper;

import java.util.List;

import lombok.Data;

@Data
public class ProductWrapper {

    private int productId;
	private String name;

	private String description;

	private String status;

	private Integer price;

	
	private String categoryName;
	
	private String imageUrl;

	public ProductWrapper() {

	}

	public ProductWrapper(int productId, String name, String description, String status, Integer price,
			String categoryName,String imageUrl) {
		super();
		this.productId=productId;
		this.name = name;
		this.description = description;
		this.status = status;
		this.price = price;
		this.categoryName=categoryName;
		this.imageUrl=imageUrl;
	}

	public ProductWrapper( String name,Integer id) {
	
		this.name = name;
	}

}

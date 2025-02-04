package com.ecommerce.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.order.dto.Product;

@FeignClient(name="product-service", url = "http://localhost:8081/product")
public interface ProductClient {

	@GetMapping("/byProductId/{productId}")
	Product getProduct(@PathVariable Integer productId);
}

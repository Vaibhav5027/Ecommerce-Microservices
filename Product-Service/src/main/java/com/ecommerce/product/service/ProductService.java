package com.ecommerce.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.wrapper.ProductWrapper;


public interface ProductService {

	ResponseEntity<String> addProduct(Map<String, String> requestMap);

	ResponseEntity<List<ProductWrapper>> getAllProduct();

	ResponseEntity<String> updateProduct(Map<String, String> requestParam);

	ResponseEntity<String> deleteProduct(Integer id);

	ResponseEntity<String> updateStatus(Map<String, String> requestMap);

	ResponseEntity<List<ProductWrapper>> getProductByGategory(Integer id);

	ResponseEntity<ProductWrapper> getProductById(Integer id);

	ResponseEntity <Page<Product>>  getProducts(int page, int size);

}

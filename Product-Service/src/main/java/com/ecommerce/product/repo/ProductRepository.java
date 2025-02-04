package com.ecommerce.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

//	List<ProductWrapper> getAllProducts();
//
////	@Transactional
////	@Modifying
////	Integer updateStatus(@Param(value = "status") String string, @Param(value = "id") Integer value);
//
//	List<ProductWrapper> getProductByCategory(@Param("id") Integer id);
//
//	ProductWrapper getProductById(@Param("id") Integer id);

}

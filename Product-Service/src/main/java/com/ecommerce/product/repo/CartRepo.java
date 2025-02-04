package com.ecommerce.product.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.product.model.CartItems;


public interface CartRepo extends JpaRepository<CartItems, Long> {

	List<CartItems> findByEmail(String email);

	CartItems findByEmailAndProductId(String email, long productId);
	
	void deleteByProductIdAndEmail(@Param("productId") long productId, @Param("email") String email);

	

}

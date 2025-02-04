package com.ecommerce.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.order.model.Order;



public interface OrderRepo extends JpaRepository<Order, Integer> {

}

package com.ecommerce.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.order.model.Bill;

public interface BillRepo extends JpaRepository<Bill, Long> {

}

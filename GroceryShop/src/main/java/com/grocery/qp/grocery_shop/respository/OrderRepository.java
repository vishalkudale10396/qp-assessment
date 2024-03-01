package com.grocery.qp.grocery_shop.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.qp.grocery_shop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}

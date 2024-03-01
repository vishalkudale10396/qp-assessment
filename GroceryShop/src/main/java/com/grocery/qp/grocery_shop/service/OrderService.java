package com.grocery.qp.grocery_shop.service;

import org.springframework.http.ResponseEntity;

import com.grocery.qp.grocery_shop.entity.Order;

public interface OrderService {

	public Order placeOrder(Order order);
}

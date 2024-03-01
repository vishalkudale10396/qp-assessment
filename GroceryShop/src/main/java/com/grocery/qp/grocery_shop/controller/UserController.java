package com.grocery.qp.grocery_shop.controller;

import java.util.List;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.qp.grocery_shop.entity.GroceryItem;
import com.grocery.qp.grocery_shop.entity.Order;
import com.grocery.qp.grocery_shop.service.GroceryService;
import com.grocery.qp.grocery_shop.service.OrderService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private GroceryService groceryService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(name = "/grocery-items", method = RequestMethod.GET)
	public ResponseEntity<List<GroceryItem>> getAvailableGroceryItems()
	{
		List<GroceryItem> availableGroceryItems=groceryService.getAvailableGroceryItems();
		return new ResponseEntity<List<GroceryItem>>(availableGroceryItems, HttpStatus.OK);
	}
	
	@RequestMapping(name = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> placeOrder(@RequestParam Order order, BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		Order PlacedOrder=orderService.placeOrder(order);
		return new ResponseEntity<>(PlacedOrder, HttpStatus.CREATED);
	}
}

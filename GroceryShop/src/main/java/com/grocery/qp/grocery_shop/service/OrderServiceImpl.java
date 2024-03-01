package com.grocery.qp.grocery_shop.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.qp.grocery_shop.entity.GroceryItem;
import com.grocery.qp.grocery_shop.entity.Order;
import com.grocery.qp.grocery_shop.respository.GroceryItemRepository;
import com.grocery.qp.grocery_shop.respository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	public OrderRepository orderRepository;
	
	@Autowired
	public GroceryItemRepository groceryItemRepository;
	
	//first will check in DB how much quantity available if it's greater than DB quantity value the quantity will set to available quantity
	@Override
	public Order placeOrder(Order order) {
		List<GroceryItem> groceryItemList=order.getGroceryItemList();
 
		for (GroceryItem groceryItem : groceryItemList) {
		 Optional<GroceryItem>	availableGroceryItem = groceryItemRepository.findById(Long.valueOf(groceryItem.getId()));
			if(groceryItem.getQuantity()>availableGroceryItem.get().getQuantity())
			{
				groceryItem.setQuantity(availableGroceryItem.get().getQuantity());
			}
		}		
		order.setGroceryItemList(groceryItemList);		
		return orderRepository.save(order);
	}

}

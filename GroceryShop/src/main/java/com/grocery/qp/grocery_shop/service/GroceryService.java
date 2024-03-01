package com.grocery.qp.grocery_shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.grocery.qp.grocery_shop.entity.GroceryItem;

@Service
public interface GroceryService {
	
	public GroceryItem addGroceryItem(GroceryItem groceryItem);
	
	public List<GroceryItem> getAllGroceryItems();
	
	public boolean removeGroceryItem(Long groceryItemId);
	
	public Optional<GroceryItem> updateGroceryItem(GroceryItem groceryitem);
	
	public List<GroceryItem> getAvailableGroceryItems();
		
}

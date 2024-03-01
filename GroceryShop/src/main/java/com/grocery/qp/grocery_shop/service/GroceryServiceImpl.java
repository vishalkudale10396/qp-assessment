package com.grocery.qp.grocery_shop.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.qp.grocery_shop.entity.GroceryItem;
import com.grocery.qp.grocery_shop.respository.GroceryItemRepository;

@Service
@Transactional
public class GroceryServiceImpl implements GroceryService{
	
	@Autowired
	private GroceryItemRepository groceryItemRepository;

	@Override
	public GroceryItem addGroceryItem(GroceryItem groceryItem) {	
		return groceryItemRepository.save(groceryItem);
	}

	@Override
	public List<GroceryItem> getAllGroceryItems() {
		return groceryItemRepository.findAll();
	}

	@Override
	public boolean removeGroceryItem(Long groceryItemId) {
		Optional<GroceryItem> optionalGroceryItem=groceryItemRepository.findById(groceryItemId);
		if(optionalGroceryItem.isPresent())
		{
			groceryItemRepository.deleteById(groceryItemId);
			return true;
		}
		return false;
	}

	@Override
	public Optional<GroceryItem> updateGroceryItem(GroceryItem groceryitem) {
		Optional<GroceryItem> existingGroceryItem= groceryItemRepository.findById(Long.valueOf(groceryitem.getId()));
		if(existingGroceryItem.isPresent())
		{
			existingGroceryItem.get().setName(groceryitem.getName());
			existingGroceryItem.get().setPrice(groceryitem.getPrice());
			existingGroceryItem.get().setQuantity(groceryitem.getQuantity());
			return Optional.of(groceryItemRepository.save(existingGroceryItem.get()));
		}

		return Optional.empty();
	}

	@Override
	public List<GroceryItem> getAvailableGroceryItems() {
		return groceryItemRepository.findByQuantityGreaterThan(0);
	}

}

package com.grocery.qp.grocery_shop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.qp.grocery_shop.entity.GroceryItem;
import com.grocery.qp.grocery_shop.service.GroceryService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private GroceryService groceryService;

	@RequestMapping(name = "/add-grocery-item", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addGroceryItem(@Valid GroceryItem groceryItem, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}

		GroceryItem addedGroceryItem = groceryService.addGroceryItem(groceryItem);
		return new ResponseEntity<>(addedGroceryItem, HttpStatus.CREATED);

	}

	@RequestMapping(name = "/grocery-items", method = RequestMethod.GET)
	public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
		List<GroceryItem> groceryItems = groceryService.getAllGroceryItems();
		return new ResponseEntity<List<GroceryItem>>(groceryItems, HttpStatus.OK);
	}

	@RequestMapping(name = "/remove-grocery-item/{groceryItemId}", method = RequestMethod.GET)
	public ResponseEntity<?> removeGroceryItem(@PathVariable Long groceryItemId) {
		boolean status = groceryService.removeGroceryItem(groceryItemId);
		if (status) {
			return new ResponseEntity<>("Grocery item removed successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Gocery item not found", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(name = "/update-grocery-item/{groceryItem}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateGroceryItem(@PathVariable GroceryItem groceryItem, BindingResult result) {
		if (result.hasErrors()) {

			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		groceryItem = groceryService.updateGroceryItem(groceryItem).get();
		return new ResponseEntity<>(groceryItem.getName() + "grocery item succefully updated", HttpStatus.OK);
	}
	
	@RequestMapping(name = "/available-grocery-items", method = RequestMethod.GET)
	public ResponseEntity<List<GroceryItem>> getAvailableGroceryItems()
	{
		List<GroceryItem> availableGroceryItems=groceryService.getAvailableGroceryItems();
		return new ResponseEntity<List<GroceryItem>>(availableGroceryItems, HttpStatus.OK);
	}
}

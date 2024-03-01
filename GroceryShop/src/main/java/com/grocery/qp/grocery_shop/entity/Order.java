package com.grocery.qp.grocery_shop.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long orderId;
	
	@Column(name = "grocery-items")
	@NotNull(message = "Please select grocery items to proceed further..")
	@NotBlank(message = "Please select at least one grocery Item")
	private List<GroceryItem> groceryItemList;

	public long getOrderId() {
		return orderId;
	}

	public List<GroceryItem> getGroceryItemList() {
		return groceryItemList;
	}

	public void setGroceryItemList(List<GroceryItem> groceryItemList) {
		this.groceryItemList = groceryItemList;
	}
	
	
}

package com.grocery.qp.grocery_shop.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grocery.qp.grocery_shop.entity.GroceryItem;
@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
List<GroceryItem> findByQuantityGreaterThan(int quantity);
	
}

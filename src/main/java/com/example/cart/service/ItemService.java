package com.example.cart.service;

import java.util.List;

import com.example.cart.model.Item;

public interface ItemService {
	
	public abstract void addItem(Item item);
	
	public abstract void deleteItem(int itemId);
	
	public abstract List<Item> getAllItems();
	
	public abstract Item getItemById(int itemId);
	
	public abstract void updateItem(Item item);
	
}

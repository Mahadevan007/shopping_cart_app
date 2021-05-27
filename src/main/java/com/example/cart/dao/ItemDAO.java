package com.example.cart.dao;

import java.util.List;

import com.example.cart.model.Item;

public interface ItemDAO {
	
	public abstract void addItem(Item item);
	
	public abstract void deleteItem(int itemId);
	
	public abstract List<Item> getAllItems();
	
	public abstract Item getItemById(int itemId);	
	
	public abstract void updateItem(Item item);
}

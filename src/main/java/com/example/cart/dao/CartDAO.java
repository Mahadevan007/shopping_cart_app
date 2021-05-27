package com.example.cart.dao;

import java.util.List;

import com.example.cart.model.Cart;
import com.example.cart.model.Item;

public interface CartDAO {
	
	public abstract List<Cart> getItemsInCart();
	
	public abstract void deleteItemByCartId(int cartId);
	
	public abstract Cart getItemByCartId(int cartId);
	
	public abstract void updateItem(Cart cart);
	
	public abstract void addItem(Item item,int UserId);
	
	public abstract void deleteAllItemsInCart();
	
	public abstract List<Cart> getItemsByUserId(int UserId);
	
	public abstract void deleteItemsByUserId(int UserId);
	
}

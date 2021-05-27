package com.example.cart.service;

import java.util.List;

import com.example.cart.model.Cart;
import com.example.cart.model.Item;

public interface CartService {
	
	public abstract List<Cart> getItemsInCart();
	
	public abstract void deleteItemByCartId(int cartId);
	
	public abstract Cart getItemByCartId(int cartId);
	
	public abstract void updateItem(Cart cart);
	
	public abstract void addItem(Item item,int UserId);
	
	public abstract void deleteItemsInCart();
	
	public abstract List<Cart> getItemsByUserId(int userId);
	
	public abstract void deleteItemsByUserId(int userId);
	
}

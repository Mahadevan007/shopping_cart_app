package com.example.cart.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.dao.CartDAO;
import com.example.cart.model.Cart;
import com.example.cart.model.Item;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDao;
	
	public CartDAO getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDAO cartDao) {
		this.cartDao = cartDao;
	}

	@Override
	public List<Cart> getItemsInCart() {
		return cartDao.getItemsInCart();
	}

	@Override
	public void deleteItemByCartId(int cartId) {
		// TODO Auto-generated method stub
		cartDao.deleteItemByCartId(cartId);
	}

	@Override
	public Cart getItemByCartId(int cartId) {
		// TODO Auto-generated method stub
		return cartDao.getItemByCartId(cartId);
	}

	@Override
	public void updateItem(Cart cart) {
		// TODO Auto-generated method stub
		cartDao.updateItem(cart);
	}

	@Override
	public void addItem(Item item,int userId) {
		// TODO Auto-generated method stub
		cartDao.addItem(item,userId);
	}

	@Override
	public void deleteItemsInCart() {
		// TODO Auto-generated method stub
		cartDao.deleteAllItemsInCart();
	}

	@Override
	public List<Cart> getItemsByUserId(int userId) {
		// TODO Auto-generated method stub
		return cartDao.getItemsByUserId(userId);
	}

	@Override
	public void deleteItemsByUserId(int userId) {
		// TODO Auto-generated method stub
		cartDao.deleteItemsByUserId(userId);
	}

}

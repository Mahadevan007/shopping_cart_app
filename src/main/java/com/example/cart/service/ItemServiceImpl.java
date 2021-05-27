package com.example.cart.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.dao.ItemDAO;
import com.example.cart.model.Item;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	public ItemDAO itemDao;
	
	public ItemDAO getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDAO itemDao) {
		this.itemDao = itemDao;
	}

	@Override
	public void addItem(Item item) {
		// TODO Auto-generated method stub
		itemDao.addItem(item);
	}

	@Override
	public void deleteItem(int itemId) {
		// TODO Auto-generated method stub
		itemDao.deleteItem(itemId);
	}

	@Override
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		return itemDao.getAllItems();
	}

	@Override
	public Item getItemById(int itemId) {
		// TODO Auto-generated method stub
		return itemDao.getItemById(itemId);
	}

}

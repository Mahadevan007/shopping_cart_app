package com.example.cart.service;

import java.util.List;

import com.example.cart.model.Invoice;
import com.example.cart.model.User;

public interface UserService {
	
	public void saveUser(User user);
	
	public List<Invoice> getInvoices(int userId);
	
	public String deleteUser(int userId);
	
	public User getUser(int userId);
	
	public User updateUser(User user);
	
	public List<User> getAllUsers();
	
	public String checkUser(String username,String password);
	
	public boolean checkFlag(String username,String password);
	
	public void changeFlag(int flag,User user);
	
	public User getLoggedInUser();
	
}

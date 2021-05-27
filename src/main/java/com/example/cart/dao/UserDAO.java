package com.example.cart.dao;

import java.util.List;

import com.example.cart.model.Invoice;
import com.example.cart.model.User;

public interface UserDAO {
	
	public abstract User saveUser(User user);
	
	public abstract User updateUser(User user);
	
	public abstract List<Invoice> getInvoices(int userId);
	
	public abstract String deleteUser(int userid);
	
	public abstract User getUser(int userId);
	
	public abstract String checkUser(String uname,String upass);
	
	public abstract List<User> getAllUsers();
	
	public boolean checkFlag(String username,String password);
	
	public void changeFlag(int flag, User user);
	
	public User getLoggedInUser();
}

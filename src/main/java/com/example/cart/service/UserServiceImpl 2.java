package com.example.cart.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.dao.UserDAO;
import com.example.cart.model.Invoice;
import com.example.cart.model.User;

@Service("us")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	
	public UserDAO getUser() {
		return userDao;
	}

	public void setUser(UserDAO user) {
		this.userDao = user;
	}
	
	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}
	
	public List<Invoice> getInvoices(int userId){
		List<Invoice> invoices = userDao.getInvoices(userId);
		return invoices;
	}

	@Override
	public String deleteUser(int userId) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(userId);
	}

	@Override
	public User getUser(int userId) {
		// TODO Auto-generated method stub
		User user = userDao.getUser(userId);
		return user;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		
		return userDao.getAllUsers();
	}

	@Override
	public String checkUser(String username, String password) {
		
		return userDao.checkUser(username, password);
	}

	@Override
	public boolean checkFlag(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void changeFlag(int flag,User user) {
		// TODO Auto-generated method stub
		userDao.changeFlag(flag,user);
	}
	
	public User getLoggedInUser() {
		return userDao.getLoggedInUser();
	}

}

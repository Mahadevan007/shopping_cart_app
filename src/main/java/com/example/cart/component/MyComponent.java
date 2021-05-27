package com.example.cart.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cart.model.User;
import com.example.cart.service.UserService;

@Component
public class MyComponent {

	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void addUser() {
		
	}
	
	
}

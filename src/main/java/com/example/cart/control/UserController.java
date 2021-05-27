package com.example.cart.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.cart.component.MyComponent;
import com.example.cart.dao.UserDAO;
import com.example.cart.model.Invoice;
import com.example.cart.model.Item;
import com.example.cart.model.User;
import com.example.cart.service.CartService;
import com.example.cart.service.ItemService;
import com.example.cart.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;
	
	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CartService cartService;

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	@RequestMapping(value="",method=RequestMethod.GET)
	public String welcome() {
		
//		cartService.deleteItemsInCart();
		
		return "welcome";
	}

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model theModel) {
		
		User user = new User();
		
		theModel.addAttribute("User",user);
		
		return "login";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String registerPage(Model theModel) {
		
		User user = new User();
		
		theModel.addAttribute("User",user);
		
		return "register";
	}
	
	@RequestMapping(value="/processRegister",method=RequestMethod.POST)
	public String processRegisterForm(@ModelAttribute("User") User theUser,Model theModel,
			HttpServletRequest request) {
		
		System.out.println(theUser.toString());
		
		theUser.setId(0);
		
		theUser.setRole("user");
		
		userService.saveUser(theUser);
		
		User user = new User();
		
		request.setAttribute("error", null);
		
		theModel.addAttribute("User",user);
		
		
		
		return "login";
	}
	
	@RequestMapping(value="/processLogin",method=RequestMethod.POST)
	public String processForm(@ModelAttribute("User") User theUser,Model theModel,
			HttpServletRequest request ) {
		
		if(request.getSession().getAttribute("CurrentUser") == null) {
		
			System.out.println("Name = "+theUser.getUsername()+" Password = "+theUser.getPassword());
		
		
			String result = userService.checkUser(theUser.getUsername(), theUser.getPassword());
			
			if(result=="not-found") {
	//			theModel.addAttribute("error-message","Your username is not available.");
				request.setAttribute("error", "Your username is not available. Try Signing Up");
				return "login";
			}else if(result=="invalid-password") {
	//			theModel.addAttribute("error-message","Your password is incorrect");
				request.setAttribute("error", "Your password is invalid");
				return "login";
			}else {
				int userId = Integer.parseInt(result);
				User user = userService.getUser(userId);
				System.out.println(user.toString());
				request.setAttribute("CurrentUser", user);
				request.getSession().setAttribute("CurrentUser", user);
				userService.changeFlag(1,user);
				request.setAttribute("error", null);
				List<Item> itemsList = itemService.getAllItems();
				request.setAttribute("items", itemsList);
				request.getSession().setAttribute("itemsList",itemsList);
			}
		}
		return "dashboard";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		
		User user = (User) request.getSession().getAttribute("CurrentUser");
		
		request.setAttribute("CurrentUser", null);
		
		request.getSession().setAttribute("CurrentUser", null);
		
		userService.changeFlag(0,user);
		
		
		return "welcome";
	}
	
	@RequestMapping(value="/settings",method=RequestMethod.GET)
	public String settingsPage(Model theModel,HttpServletRequest request) {
		
		User user = userService.getLoggedInUser();
		
		System.out.println("User ===== "+user);
		
		theModel.addAttribute("User",user);
		
		return "Settings";
	}
	
	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public String showDashboard(Model theModel,HttpServletRequest request) {
		
		List<Item> itemsList = itemService.getAllItems();
		
		request.setAttribute("items",itemsList );
		
		return "dashboard";
	}
	
	@RequestMapping(value="/updateForm",method=RequestMethod.POST)
	public String updateForm(@ModelAttribute User theUser,Model theModel,HttpServletRequest request) {
		
		User user = userService.updateUser(theUser);
		
		request.setAttribute("CurrentUser", user);
		
		theModel.addAttribute("User",user);
		
		return "dashboard";
	}
	
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}

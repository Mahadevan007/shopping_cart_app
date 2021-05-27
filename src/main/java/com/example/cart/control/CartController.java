package com.example.cart.control;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cart.model.Cart;
import com.example.cart.model.ChargeRequest;
import com.example.cart.model.Item;
import com.example.cart.model.User;
import com.example.cart.service.CartService;
import com.example.cart.service.ItemService;
import com.stripe.Stripe;

@Controller
@RequestMapping("/item")
public class CartController {

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
	
	@Value("${STRIPE_PUBLIC_KEY}")
	private String stripePublicKey;
	
	@Value("${STRIPE_SECRET_KEY}")
	private String secretKey;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam("id") int id, HttpServletRequest theRequest) {
		System.out.println(id);

		Item item = itemService.getItemById(id);
		
		User user = (User) theRequest.getSession().getAttribute("CurrentUser");

		System.out.println(user.getId());
		
		cartService.addItem(item,user.getId());

		System.out.println("Item added");

		List<Cart> cartItems = cartService.getItemsByUserId(user.getId());
		
		Iterator<Cart> itr = cartItems.iterator();
		
		int sum = 0;
		
		while(itr.hasNext()) {
		
			sum = sum + itr.next().getItem_totalamount();
			
		}
		
		theRequest.setAttribute("totalAmount", sum);

		System.out.println(cartItems.toString());

//		User user = (User) theRequest.getSession().getAttribute("CurrentUser");
		
		theRequest.setAttribute("cartItems", cartItems);
		
		theRequest.getSession().setAttribute("cartItems",cartItems);
		
		theRequest.getSession().setAttribute("totalAmount", sum);

		System.out.println(user.toString());

		return "redirect:/item/showCart";
	}

	@RequestMapping(value = "/addupdate/{itemid}", method = RequestMethod.GET)
	public String addUpdate(@PathVariable(value = "itemid") int id, 
				HttpServletRequest theRequest) {

		System.out.println(id);
		
		Cart cart = cartService.getItemByCartId(id);
		
		if(cart.getItem_quantity() < cart.getItem_total_quantity()) {
		
			int amount = cart.getItem_totalamount() / cart.getItem_quantity();
			
			int quantity = cart.getItem_quantity() + 1 ;
			
			amount = amount * quantity;
			
			cart.setItem_totalamount(amount);
			
			cart.setItem_quantity(quantity);
			
			cartService.updateItem(cart);
			
			User user = (User) theRequest.getSession().getAttribute("CurrentUser");
			
			List<Cart> cartItems = cartService.getItemsByUserId(user.getId());
			
			Iterator<Cart> itr = cartItems.iterator();
			
			int sum = 0;
			
			while(itr.hasNext()) {
			
				sum = sum + itr.next().getItem_totalamount();
				
			}
			
			theRequest.setAttribute("totalAmount", sum);
			
			theRequest.getSession().setAttribute("totalAmount", sum);
			
			theRequest.setAttribute("cartItems", cartItems);
			
			theRequest.getSession().setAttribute("cartItems",cartItems);
		
		}
		
		return "redirect:/item/showCart";
	}
	
	@RequestMapping(value="/showCart",method = RequestMethod.GET)
	public String viewCart(HttpServletRequest theRequest) {
		
		User user = (User) theRequest.getSession().getAttribute("CurrentUser");
		
		List<Cart> cartItems = cartService.getItemsByUserId(user.getId());
		
		theRequest.setAttribute("cartItems", cartItems);
		
		theRequest.getSession().setAttribute("cartItems",cartItems);
		
		return "cart";
	}

	@RequestMapping(value = "/deleteupdate/{itemid}", method = RequestMethod.GET)
	public String deleteUpdate(@PathVariable(value = "itemid") int id, 
				HttpServletRequest theRequest) {

		System.out.println(id);
		
		Cart cart = cartService.getItemByCartId(id);
		
		if(cart.getItem_quantity() != 1) {
			
			int amount = cart.getItem_totalamount() / cart.getItem_quantity();
			
			int quantity = cart.getItem_quantity() - 1 ;
			
			amount = amount * quantity;
			
			cart.setItem_totalamount(amount);
			
			cart.setItem_quantity(quantity);
			
			cartService.updateItem(cart);
			
			User user = (User) theRequest.getSession().getAttribute("CurrentUser");
			
			List<Cart> cartItems = cartService.getItemsByUserId(user.getId());
			
			Iterator<Cart> itr = cartItems.iterator();
			
			int sum = 0;
			
			while(itr.hasNext()) {
			
				sum = sum + itr.next().getItem_totalamount();
				
			}
			
			theRequest.setAttribute("totalAmount", sum);
			
			theRequest.getSession().setAttribute("totalAmount", sum);
			
			theRequest.setAttribute("cartItems", cartItems);
			
			theRequest.getSession().setAttribute("cartItems",cartItems);
		
		}

		return "redirect:/item/showCart";
	}
	
	@RequestMapping(value = "/deleteItem/{cartId}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable(value = "cartId") int id,
			HttpServletRequest theRequest) {

		System.out.println(id);
		
		Cart cart = cartService.getItemByCartId(id);
		
		cartService.deleteItemByCartId(id);
		
		User user = (User) theRequest.getSession().getAttribute("CurrentUser");
		
		List<Cart> cartItems = cartService.getItemsByUserId(user.getId());
		
		Iterator<Cart> itr = cartItems.iterator();
		
		int sum = 0;
		
		while(itr.hasNext()) {
		
			sum = sum + itr.next().getItem_totalamount();
			
		}
		
		System.out.println(cartItems.toString());
		
		if(cartItems.isEmpty()) {
			return "redirect:/dashboard";
		}
		
		theRequest.setAttribute("totalAmount", sum);
		
		theRequest.getSession().setAttribute("totalAmount", sum);
		
		theRequest.setAttribute("cartItems", cartItems);
		
		theRequest.getSession().setAttribute("cartItems",cartItems);

		return "redirect:/item/showCart";
	}
	
	@RequestMapping(value = "/confirmation",method=RequestMethod.POST)
	public String showConfirmation(HttpServletRequest theRequest,Model model) {
		
		User user = (User) theRequest.getSession().getAttribute("CurrentUser");
		
		List<Cart> cartItems = cartService.getItemsByUserId(user.getId());
		
		Iterator<Cart> itr = cartItems.iterator();
		
		int sum = 0;
		
		while(itr.hasNext()) {
		
			sum = sum + itr.next().getItem_totalamount();
			
		}
//		
//		theRequest.setAttribute("cartItems", cartItems);
//		
//		theRequest.getSession().setAttribute("cartItems",cartItems);
//		
		theRequest.setAttribute("orderAmount", sum);
//		
		theRequest.getSession().setAttribute("orderAmount", sum);
		
		float tax = (sum * 30)/100;
		
		float totalCost = sum + tax + 50;
		
		System.out.println("Tax===="+tax);
		
		theRequest.getSession().setAttribute("tax", tax);
		
		theRequest.getSession().setAttribute("shipping-cost", 50);
//		
		theRequest.setAttribute("tax", tax);
//		
		theRequest.getSession().setAttribute("totalCost", totalCost);
//		
		theRequest.setAttribute("shipping-cost", 50);
		
		theRequest.setAttribute("amount", 1);
		
		theRequest.setAttribute("user", user);
		
		theRequest.setAttribute("stripePublicKey", stripePublicKey);
		
		theRequest.setAttribute("stripeSecretKey", secretKey);
        
        Stripe.apiKey = secretKey;
        
        model.addAttribute("amount",totalCost * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.INR);
        
//        return "checkout";
		
		return "confirmation";
		
		
	}

	@RequestMapping(value = "/additem", method = RequestMethod.GET)
	public String itemadd() {
		
		Item item = new Item();
		item.setImageurl("https://static.toiimg.com/thumb/63625118.cms?width=1200&height=900");
		item.setItem_name("Nachos");
		item.setPrice(40);
		item.setCategory("C");
		item.setQuantity(15);

		Item item2 = new Item();
		item2.setImageurl("https://media.istockphoto.com/photos/tomato-isolated-on-white-background-picture-id466175630?k=6&m=466175630&s=612x612&w=0&h=fu_mQBjGJZIliOWwCR0Vf2myRvKWyQDsymxEIi8tZ38=");
		item2.setItem_name("Tomatoes");
		item2.setPrice(20);
		item2.setCategory("A");
		item2.setQuantity(15);
	
		Item item3 = new Item();
		item3.setImageurl("https://cdn.britannica.com/w:1100/89/170689-131-D20F8F0A/Potatoes.jpg");
		item3.setItem_name("Potatos");
		item3.setPrice(20);
		item3.setCategory("A");
		item3.setQuantity(15);
		
		Item item4 = new Item();
		item4.setImageurl("https://m.economictimes.com/thumb/msid-70851131,width-1200,height-900,resizemode-4,imgsize-442430/biscuit-agencies.jpg");
		item4.setItem_name("Biscuits");
		item4.setPrice(20);
		item4.setCategory("C");
		item4.setQuantity(15);
	
		Item item5 = new Item();
		item5.setImageurl("http://cdn.shopify.com/s/files/1/0699/8747/products/biomed_black_toothbrush_blue_1024x1024_c4747464-39a1-43fb-b39d-9f4a2ca5a27b_grande.jpg?v=1586096926");
		item5.setItem_name("ToothBrush");
		item5.setPrice(20);
		item5.setCategory("B");
		item5.setQuantity(15);
		
		Item item6 = new Item();
		item6.setImageurl("https://d3hjf51r9j54j7.cloudfront.net/wp-content/uploads/sites/7/2015/12/Toothpaste3-620x330.jpg");
		item6.setItem_name("Toothpaste");
		item6.setPrice(10);
		item6.setCategory("B");
		item6.setQuantity(15);

		Item item8 = new Item();
		item8.setImageurl("https://5.imimg.com/data5/IM/GA/MY-49476163/fresh-radish-500x500.jpeg");
		item8.setItem_name("Raddish");
		item8.setPrice(20);
		item8.setCategory("A");
		item8.setQuantity(15);
		
		Item item9 = new Item();
		item9.setImageurl("http://cdn.shopify.com/s/files/1/1380/2059/products/Carrot-Orange_grande.jpg?v=1598079671");
		item9.setItem_name("Carrot");
		item9.setPrice(20);
		item9.setCategory("A");
		item9.setQuantity(15);
		
		Item item13= new Item();
		item13.setImageurl("https://media.istockphoto.com/photos/red-onion-slice-picture-id175448479?k=6&m=175448479&s=612x612&w=0&h=USNri9uLcKbsi25EEqiW71439WX0y5weMtDha27BhNs=");
		item13.setItem_name("Onion");
		item13.setPrice(20);
		item13.setCategory("A");
		item13.setQuantity(15);
		
		Item item14= new Item();
		item14.setImageurl("https://images-na.ssl-images-amazon.com/images/I/616PXhYj8iL._SL1000_.jpg");
		item14.setItem_name("Beetroot");
		item14.setPrice(10);
		item14.setCategory("A");
		item14.setQuantity(15);
	
		Item item15 = new Item();
		item15.setImageurl("https://cdn.thomasnet.com/insights-images/embedded-images/4399ffd9-4ce9-4ba7-a008-c41bed53921a/cec9fc52-c432-4d7d-8583-77c18c6e7601/FullHD/private-label-soap-suppliers.jpg");
		item15.setItem_name("Soap");
		item15.setPrice(10);
		item15.setCategory("B");
		item15.setQuantity(15);
		
		Item item16 = new Item();
		item16.setImageurl("https://chocolatecoveredkatie.com/wp-content/uploads/2020/08/Homemade-Chocolate-Bars.jpg");
		item16.setItem_name("Chocolates");
		item16.setPrice(10);
		item16.setCategory("C");
		item16.setQuantity(15);
		
		Item item17= new Item();
		item17.setImageurl("https://images.pexels.com/photos/102104/pexels-photo-102104.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
		item17.setItem_name("Apple");
		item17.setPrice(40);
		item17.setCategory("A");
		item17.setQuantity(15);
		
		Item item18= new Item();
		item18.setImageurl("https://thumbs.dreamstime.com/b/lemon-isolated-24834437.jpg");
		item18.setItem_name("Lemon");
		item18.setPrice(15);
		item18.setCategory("A");
		item18.setQuantity(15);
	
		Item item20 = new Item();
		item20.setImageurl("https://i0.wp.com/images-prod.healthline.com/hlcmsresource/images/topic_centers/2020-7/oats-oatmeal-1296x728-header.jpg?w=1155&h=1528");
		item20.setItem_name("Oats");
		item20.setPrice(20);
		item20.setCategory("C");
		item20.setQuantity(15);
		
		Item item21 = new Item();
		item21.setImageurl("https://thestayathomechef.com/wp-content/uploads/2019/10/Homemade-Bread-4.jpg");
		item21.setItem_name("Bread");
		item21.setPrice(25);
		item21.setCategory("C");
		item21.setQuantity(15);
		
		Item item22= new Item();
		item22.setImageurl("https://image.shutterstock.com/image-photo/whole-half-watermelon-isolated-on-260nw-1422071948.jpg");
		item22.setItem_name("Watermelon");
		item22.setPrice(40);
		item22.setCategory("A");
		item22.setQuantity(15);
		
		Item item23= new Item();
		item23.setImageurl("https://www.aicr.org/wp-content/uploads/2020/01/shutterstock_533487490-640x462.jpg");
		item23.setItem_name("Grapes");
		item23.setPrice(25);
		item23.setCategory("A");
		item23.setQuantity(15);

		itemService.addItem(item);
		itemService.addItem(item2);
		itemService.addItem(item3);
		itemService.addItem(item4);
		itemService.addItem(item5);
		itemService.addItem(item6);
		itemService.addItem(item8);
		itemService.addItem(item9);
		itemService.addItem(item13);
		itemService.addItem(item14);
		itemService.addItem(item15);
		itemService.addItem(item16);
		itemService.addItem(item17);
		itemService.addItem(item18);
		itemService.addItem(item20);
		itemService.addItem(item21);
		itemService.addItem(item22);
		itemService.addItem(item23);
		
		return "test";
	}

}

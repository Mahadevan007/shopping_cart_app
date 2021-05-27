package com.example.cart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="temp_cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="item_id")
	private int item_id;
	
	@Column(name="item_name")
	private String item_name;
	
	@Column(name="item_quantity")
	private int item_quantity;
	
	@Column(name="item_totalamount")
	private int item_totalamount;
	
	@Column(name="category")
	private String category;
	
	@Column(name="image_url")
	private String image_url;
	
	@Column(name="item_total_quantity")
	private int item_total_quantity;
	
	@Column(name="item_price")
	private int item_price;
	
	@Column(name="user_id")
	private int user_id;
	

	public Cart(int item_id, String item_name, int item_quantity, int item_totalamount, String category,
			String image_url, int item_total_quantity, int item_price, int user_id) {
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_quantity = item_quantity;
		this.item_totalamount = item_totalamount;
		this.category = category;
		this.image_url = image_url;
		this.item_total_quantity = item_total_quantity;
		this.item_price = item_price;
		this.user_id = user_id;
	}

	public int getItem_price() {
		return item_price;
	}

	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}

	public int getItem_total_quantity() {
		return item_total_quantity;
	}

	public void setItem_total_quantity(int item_total_quantity) {
		this.item_total_quantity = item_total_quantity;
	}

	public Cart() {
		
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getItem_quantity() {
		return item_quantity;
	}

	public void setItem_quantity(int item_quantity) {
		this.item_quantity = item_quantity;
	}

	public int getItem_totalamount() {
		return item_totalamount;
	}

	public void setItem_totalamount(int item_totalamount) {
		this.item_totalamount = item_totalamount;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", item_id=" + item_id + ", item_name=" + item_name + ", item_quantity="
				+ item_quantity + ", item_totalamount=" + item_totalamount + ", category=" + category + ", image_url="
				+ image_url + ", item_total_quantity=" + item_total_quantity + ", item_price=" + item_price
				+ ", user_id=" + user_id + "]";
	}

	
	
}

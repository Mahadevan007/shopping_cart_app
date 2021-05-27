package com.example.cart.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="item")
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item_id")
	private int id;
	
	@Column(name="name")
	private String item_name;
	
	@Column(name="price")
	private int price;
	
	@Column(name="image_url")
	private String imageurl;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="category")
	private String category;
	
//	private List<Invoice> invoices;
	
	public Item() {
		
	}

	public Item(String item_name, int price, String imageurl, int quantity, String category) {
		this.item_name = item_name;
		this.price = price;
		this.imageurl = imageurl;
		this.quantity = quantity;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

//	public List<Invoice> getInvoices() {
//		return invoices;
//	}
//
//	public void setInvoices(List<Invoice> invoices) {
//		this.invoices = invoices;
//	}
//	
//	public void addInvoice(Invoice invoice) {
//		
//		if(invoices == null) {
//			invoices = new ArrayList<>();
//		}
//		
//		invoices.add(invoice);
//	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", item_name=" + item_name + ", price=" + price + ", imageurl=" + imageurl
				+ ", quantity=" + quantity + ", category=" + category + "]";
	}
	
}

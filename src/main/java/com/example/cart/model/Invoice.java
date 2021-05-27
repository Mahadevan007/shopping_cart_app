package com.example.cart.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="invoice")
public class Invoice {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="invoice_id")
	private int id;
	
	@Column(name="date")
	private Date date;

	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
							CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="invoice_items",
			   joinColumns=@JoinColumn(name="invoice_id"),
			   inverseJoinColumns=@JoinColumn(name="item_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(fetch=FetchType.EAGER,
//			cascade=CascadeType.ALL)
//	@JoinColumn(name="item_id")
	private List<Item> items;
	
	@Column(name="subtotal")
	private int subtotal; 
	
	@Column(name="shipping")
	private int shipping;
	
	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

	public int getShipping() {
		return shipping;
	}

	public void setShipping(int shipping) {
		this.shipping = shipping;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Column(name="tax")
	private int tax;
	
	@Column(name="total")
	private int total;
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Invoice() {
		
	}
	
	public Invoice(Date date, int subtotal, int shipping, int tax, int total) {
		this.date = date;
		this.subtotal = subtotal;
		this.shipping = shipping;
		this.tax = tax;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", date=" + date + ", items=" + items + ", subtotal=" + subtotal + ", shipping="
				+ shipping + ", tax=" + tax + ", total=" + total + "]";
	}

	public void addItem(Item item) {
		
		if(items == null) {
			items = new ArrayList<>();
		}
		
		items.add(item);
		
	}
	
}

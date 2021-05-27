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
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="User")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="address1")
	private String address1;
	
	@Column(name="address2")
	private String address2;
	
	@Column(name="pincode")
	private int pincode;
	
	@Column(name="phonenumber")
	private long phonenumber;
	
	@Column(name="flag")
	private int flag;
	
	@Column(name="email")
	private String email;
	
	@Column(name="role")
	private String role;
	
	@OneToMany(fetch=FetchType.EAGER,
				cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<Invoice> invoices;
	
	public User() {
		
	}

	public User(String username, String password, String address1, String address2, int pincode, long phonenumber,
			int flag, String email, String role) {
		this.username = username;
		this.password = password;
		this.address1 = address1;
		this.address2 = address2;
		this.pincode = pincode;
		this.phonenumber = phonenumber;
		this.flag = flag;
		this.email = email;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", address1=" + address1
				+ ", address2=" + address2 + ", pincode=" + pincode + ", phonenumber=" + phonenumber + ", flag=" + flag
				+ ", email=" + email + ", role=" + role + ", invoices=" + invoices + "]";
	}
	
	public void addInvoice(Invoice tempInvoice) {
		
		if(invoices == null) {
			invoices = new ArrayList<>();
		}
		
		invoices.add(tempInvoice);
		
	}
	

}














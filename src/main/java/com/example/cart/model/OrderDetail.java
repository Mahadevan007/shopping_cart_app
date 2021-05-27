package com.example.cart.model;

import java.util.List;

public class OrderDetail {
	
	private List<Item> products;
    private float subtotal;
    private float shipping;
    private float tax;
    private float total;
 
    public OrderDetail(List<Item> productName, String subtotal,
            String shipping, String tax, String total) {
        this.products = productName;
        this.subtotal = Float.parseFloat(subtotal);
        this.shipping = Float.parseFloat(shipping);
        this.tax = Float.parseFloat(tax);
        this.total = Float.parseFloat(total);
    }
 
    public List<Item> getProducts() {
        return products;
    }
 
    public String getSubtotal() {
        return String.format("%.2f", subtotal);
    }
 
    public String getShipping() {
        return String.format("%.2f", shipping);
    }
 
    public String getTax() {
        return String.format("%.2f", tax);
    }
     
    public String getTotal() {
        return String.format("%.2f", total);
    }
	
}

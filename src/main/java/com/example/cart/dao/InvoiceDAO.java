package com.example.cart.dao;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.example.cart.model.Invoice;
import com.example.cart.model.Item;

public interface InvoiceDAO {
	
	public Invoice getInvoice(int invoiceId);
	
	public String deleteInvoice(int invoiceId);
	
	public Invoice getInvoiceByDate(Date date);
	
	public List<Item> getItems(int invoiceId);
	
	public Invoice saveInvoiceAndItems(Invoice invoice,List<Item> items);
	
}

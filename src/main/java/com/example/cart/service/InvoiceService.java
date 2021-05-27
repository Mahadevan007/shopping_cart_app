package com.example.cart.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.cart.model.Invoice;
import com.example.cart.model.Item;

public interface InvoiceService {
	
	public Invoice getInvoice(int invoiceId);
	
	public String deleteInvoice(int invoiceId);
	
	public Invoice getInvoiceByDate(Date date);
	
	public List<Item> getItems(int invoiceId);
	
	public Invoice saveInvoiceAndItems(Invoice invoice,List<Item> items);
	
}

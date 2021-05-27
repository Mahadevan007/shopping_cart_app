package com.example.cart.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.dao.InvoiceDAO;
import com.example.cart.dao.UserDAO;
import com.example.cart.model.Invoice;
import com.example.cart.model.Item;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceDAO invoiceDAO;
	
	public InvoiceDAO getUser() {
		return invoiceDAO;
	}

	public void setUser(InvoiceDAO invoiceDAO) {
		this.invoiceDAO = invoiceDAO;
	}
	
	@Override
	public Invoice getInvoice(int invoiceId) {
		return invoiceDAO.getInvoice(invoiceId);
	}

	@Override
	public String deleteInvoice(int invoiceId) {
		
		return invoiceDAO.deleteInvoice(invoiceId);
	}

	@Override
	public Invoice getInvoiceByDate(Date date) {

		return invoiceDAO.getInvoiceByDate(date);
	}

	@Override
	public List<Item> getItems(int invoiceId) {

		return invoiceDAO.getItems(invoiceId);
	}

	@Override
	public Invoice saveInvoiceAndItems(Invoice invoice, List<Item> items) {

		return invoiceDAO.saveInvoiceAndItems(invoice, items);
	}

}

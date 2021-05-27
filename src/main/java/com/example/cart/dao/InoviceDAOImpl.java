package com.example.cart.dao;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.cart.model.Invoice;
import com.example.cart.model.Item;
import com.example.cart.model.User;

@Repository
public class InoviceDAOImpl implements InvoiceDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Invoice getInvoice(int invoiceId) {
		
		Session session=sessionFactory.getCurrentSession();
		
		Invoice invoice = session.get(Invoice.class, invoiceId);
		
		return invoice;
	}

	@Override
	public String deleteInvoice(int invoiceId) {
		
		Session session=sessionFactory.getCurrentSession();
		
		Invoice invoice = session.get(Invoice.class, invoiceId);
		
		session.delete(invoice);
		
		return "Invoice Deleted";
	}

	@Override
	public Invoice getInvoiceByDate(Date date) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Invoice where date=:invoiceDate",Invoice.class);
		
		query.setParameter("invoiceDate", date);
		
		Invoice invoice = (Invoice) query.getResultList();
		
		System.out.println(invoice.toString());
		
		return null;
	}

	@Override
	public List<Item> getItems(int invoiceId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		
		Invoice invoice = session.get(Invoice.class, invoiceId);
		
		List<Item> items = invoice.getItems();
		
		return items;
	}

	@Override
	public Invoice saveInvoiceAndItems(Invoice invoice, List<Item> items) {
		
		Session session=sessionFactory.getCurrentSession();
		
		Iterator<Item> itr = items.iterator();
		
		while(itr.hasNext()) {
			invoice.addItem(itr.next());
		}
		
		session.update(invoice);
		
		Iterator<Item> itr2 = items.iterator();
		
		System.out.println("Saving items");
		
//		while(itr2.hasNext()) {
//			session.save(itr2.next());
//		}
		
		System.out.println("Saved items :"+invoice.getItems());
		
		return invoice;
	}

}

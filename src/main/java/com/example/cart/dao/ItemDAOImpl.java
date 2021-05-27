package com.example.cart.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.cart.model.Item;
import com.example.cart.model.User;

@Repository
public class ItemDAOImpl implements ItemDAO {

	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addItem(Item item) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		System.out.println(".................session factory.................:"+sessionFactory);
		session.persist(item);
		System.out.println("done");
	}

	@Override
	public void deleteItem(int itemId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		System.out.println(".................session factory.................:"+sessionFactory);
		
		Item item = session.get(Item.class, itemId);
		
		session.delete(item);
		
		System.out.println("done");
	}

	@Override
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		Query<Item> theQuery = session.createQuery("from Item order by name",Item.class);
		
		List<Item> items = theQuery.getResultList();
		
		return items;
	}
	@Override
	public Item getItemById(int itemId) {
			
		Session session=sessionFactory.getCurrentSession();
		System.out.println(".................session factory.................:"+sessionFactory);
		
		Item item = session.get(Item.class, itemId);
		
		return item;
	}

}

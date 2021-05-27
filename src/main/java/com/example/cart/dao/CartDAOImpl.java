package com.example.cart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.cart.model.Cart;
import com.example.cart.model.Item;
import com.example.cart.model.User;

@Repository
public class CartDAOImpl implements CartDAO {

	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Cart> getItemsInCart() {

		Session session = sessionFactory.getCurrentSession();

		Query<Cart> theQuery = session.createQuery("from Cart order by id", Cart.class);

		List<Cart> cartItems = theQuery.getResultList();

		return cartItems;

	}

	@Override
	public void deleteItemByCartId(int cartId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		System.out.println(".................session factory.................:" + sessionFactory);

		Cart cart = session.get(Cart.class, cartId);

		session.delete(cart);

		System.out.println("done");
	}

	@Override
	public Cart getItemByCartId(int cartId) {
		Session session = sessionFactory.getCurrentSession();

		System.out.println(".................session factory.................:" + sessionFactory);

		Cart cart = session.get(Cart.class, cartId);

		return cart;
	}

	@Override
	public void updateItem(Cart cart) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

		System.out.println(".................session factory.................:" + sessionFactory);

		session.update(cart);
	}

	@Override
	public void addItem(Item item, int userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

		System.out.println(".................session factory.................:" + sessionFactory);

		Criteria crit = session.createCriteria(Cart.class);

		crit.add(Restrictions.eq("user_id", userId));

		List<Cart> results1 = crit.list();

		if (results1.isEmpty()) {

			Cart cart = new Cart(item.getId(), item.getItem_name(), 1, item.getPrice(), item.getCategory(),
					item.getImageurl(), item.getQuantity(), item.getPrice(), userId);

			session.persist(cart);
		} else {

			crit.add(Restrictions.eq("item_id", item.getId()));

			List<Cart> results = crit.list();

			if (results.isEmpty()) {

				Cart cart = new Cart(item.getId(), item.getItem_name(), 1, item.getPrice(), item.getCategory(),
						item.getImageurl(), item.getQuantity(), item.getPrice(), userId);

				session.persist(cart);

			} else {

				Cart cart = results.get(0);

				if (cart.getItem_quantity() < cart.getItem_total_quantity()) {

					int amount = cart.getItem_totalamount() / cart.getItem_quantity();

					int quantity = cart.getItem_quantity() + 1;

					amount = amount * quantity;

					cart.setItem_totalamount(amount);

					cart.setItem_quantity(quantity);

					session.update(cart);

				}
			}
		}
	}

	@Override
	public void deleteAllItemsInCart() {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().createQuery("delete from Cart").executeUpdate();
	}

	@Override
	public List<Cart> getItemsByUserId(int UserId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

		System.out.println(".................session factory.................:" + sessionFactory);

		Criteria crit = session.createCriteria(Cart.class);

		crit.add(Restrictions.eq("user_id", UserId));

		List<Cart> results = crit.list();

		return results;
	}

	@Override
	public void deleteItemsByUserId(int UserId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("delete Cart where user_id=:userId");

		query.setParameter("userId", UserId);

		int result = query.executeUpdate();

		System.out.println("Items Deleted " + result);
	}

}

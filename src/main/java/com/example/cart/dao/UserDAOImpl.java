package com.example.cart.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.cart.model.Invoice;
import com.example.cart.model.Item;
import com.example.cart.model.User;



@Repository
public class UserDAOImpl implements UserDAO{

	private User loggedInUser;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public User getLoggedInUser() {
		return loggedInUser;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		System.out.println(".................session factory.................:"+sessionFactory);
		session.persist(user);
		System.out.println("Done !");
		return user;
	}
	
	@Override
	public List<Invoice> getInvoices(int userid){
		Session session=sessionFactory.getCurrentSession();
		User user = session.get(User.class, userid);
		System.out.println(user.toString());
		List<Invoice> invoices = user.getInvoices();
		return invoices;
	}
	
	@Override
	public String deleteUser(int userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
//		// delete object with primary key
//		Query theQuery = session.createQuery("delete from User where user_id=:userId");
//		
//		theQuery.setParameter("userId", userId);
//		
//		theQuery.executeUpdate();
		
		User user = session.get(User.class, userId);
		
		session.delete(user);
		
		return "User Deleted";
	}
	
	@Override
	public User getUser(int userId) {
		// TODO Auto-generated method stub
		
		Session session=sessionFactory.getCurrentSession();
		
		User user = session.get(User.class, userId);
		
		return user;
	}
	
	@Override
	public String checkUser(String uname, String upass) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		
		Criteria crit = session.createCriteria(User.class);
		
		crit.add(Restrictions.eq("username", uname));
		
		List<User> results = crit.list();
		
		if(results.isEmpty()) {
			return "not-found";
		}
		
		User user = results.get(0);
		
		System.out.println("Password ====  "+user.getPassword());
		
		if(!user.getPassword().equals(upass)) {
			return "invalid-password";
		}
		
		loggedInUser = user;
		
		System.out.println(results.toString());
		
		return ""+user.getId();
	}
	
	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		
		session.update(user);
		
		User user2 = session.get(User.class, user.getId());
		
		loggedInUser = user2;
		
		return user2;
	}
	
	@Override
	public List<User> getAllUsers() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<User> theQuery = session.createQuery("from User order by username",User.class);
		
		List<User> users = theQuery.getResultList();
		
		return users;
	}
	@Override
	public boolean checkFlag(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void changeFlag(int flag,User user) {
		// TODO Auto-generated method stub
		user.setFlag(flag);
		
		Session session = sessionFactory.getCurrentSession();
		
		session.update(user);
	}	
	
}

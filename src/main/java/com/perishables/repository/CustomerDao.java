package com.perishables.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.perishables.model.Customer;

@Repository
public class CustomerDao {
	@Autowired
	private SessionFactory sf;
	
	public Long save(Customer c) {
		Session session = sf.openSession();
		session.beginTransaction();
		Long id = (Long)session.save(c);
		session.getTransaction().commit();
		session.close();
		return id;
	}
	
	public Customer login(String email) {
		Session session = sf.openSession();
		session.beginTransaction();
		
//		Fetch the record based on email
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("FROM com.perishables.model.Customer WHERE email='" + email + "'");
		Customer c = null;
		@SuppressWarnings("deprecation")
		List<Customer> q = query.list();
		
		if(!q.isEmpty())
			c = q.get(0);
		
		session.getTransaction().commit();
		session.close();
		return c;
	}
	
	public void delete(Customer c) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();
		session.close();
	}
	
//	public void update(Customer c) {
//		Session session = sf.openSession();
//		session.beginTransaction();
//		
//		@SuppressWarnings("rawtypes")
//		Query q = session.createQuery("update Customer set name=:name, password=:password, email=:email, dob=:dob, address=:address, mobNo=:mobNo where id=:id");
//		q.setParameter("name", c.getName());
//		q.setParameter("password", c.getPassword());
//		q.setParameter("email", c.getEmail());
//		q.setParameter("dob", c.getDob());
//		q.setParameter("address", c.getAddress());
//		q.setParameter("mobNo", c.getmobNo());
//		q.setParameter("id", c.getId());
//		int status = q.executeUpdate();
//		System.out.println(status);
//		session.getTransaction().commit();
//		session.close();
//	}
}

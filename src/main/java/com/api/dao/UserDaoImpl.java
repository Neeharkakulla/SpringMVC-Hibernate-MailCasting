package com.api.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.model.UserModel;

@Repository

public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public UserModel findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		String SQL_QUERY ="from UserModel as u where u.email=?";
		Query<UserModel> query = session.createQuery(SQL_QUERY,UserModel.class);
		query.setParameter(0,email);
		UserModel user=null;
		try {
		 user = query.getSingleResult();
		}
		catch (Exception e) {
		}
		
	
		return user;
		
	}

	@Override
	public UserModel findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		UserModel user = session.get(UserModel.class, id);
		return user;

	}

	@Override
	public void save(UserModel user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

}

package com.api.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.model.InBoxModel;
import com.api.model.UserModel;

@Repository
public class InBoxDaoImpl implements InBoxDao {
	
	@Autowired
	private SessionFactory sessionFactory;


	@SuppressWarnings("unchecked")
	@Override
	public List<InBoxModel> findAllByReciever(String reciever) {
		Session session = sessionFactory.getCurrentSession();
		String SQL_QUERY ="from InBoxModel as u where u.reciever=?";
		Query query = session.createQuery(SQL_QUERY,InBoxModel.class);
		query.setParameter(0,reciever);
		List<InBoxModel> mails=null;
		try {
		 mails = query.getResultList();
		}
		catch (Exception e) {
		}
		
	
		return mails;
	
	}

	@Override
	public InBoxModel findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		InBoxModel mail = session.get(InBoxModel.class, id);
		return mail;
	}

	@Override
	public void save(InBoxModel mail) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(mail);
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		InBoxModel mail = session.byId(InBoxModel.class).load(id);
		session.delete(mail);
	}

}

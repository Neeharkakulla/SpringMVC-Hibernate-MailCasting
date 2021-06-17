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
import com.api.model.SentBoxModel;

@Repository
public class SentBoxDaoImpl implements SentBoxDao{
	
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void save(SentBoxModel mail) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(mail);
	}

	@Override
	public SentBoxModel findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		SentBoxModel mail = session.get(SentBoxModel.class, id);
		return mail;

	}

	@Override
	public List<SentBoxModel> findAllBySender(String sender) {
		Session session = sessionFactory.getCurrentSession();
		String SQL_QUERY ="from SentBoxModel as u where u.sender=?";
		Query query = session.createQuery(SQL_QUERY,SentBoxModel.class);
		query.setParameter(0,sender);
		List<SentBoxModel> mails=null;
		try {
		 mails = query.getResultList();
		}
		catch (Exception e) {
		}
		
	
		return mails;

	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		SentBoxModel mail = session.byId(SentBoxModel.class).load(id);
		session.delete(mail);

	}

}

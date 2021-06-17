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

import com.api.model.BinModel;
import com.api.model.SentBoxModel;

@Repository
public class BinDaoImpl implements BinDao {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void save(BinModel mail) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(mail);
	}

	@Override
	public BinModel findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		BinModel mail = session.get(BinModel.class, id);
		return mail;

	}

	@Override
	public List<BinModel> findAllByUserMail(String usermail) {
		Session session = sessionFactory.getCurrentSession();
		String SQL_QUERY ="from BinModel as u where u.usermail=?";
		Query query = session.createQuery(SQL_QUERY,BinModel.class);
		query.setParameter(0,usermail);
		List<BinModel> mails=null;
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
		BinModel mail = session.byId(BinModel.class).load(id);
		session.delete(mail);

	}

}

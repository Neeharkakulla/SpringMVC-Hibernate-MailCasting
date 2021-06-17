package com.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.dao.InBoxDao;
import com.api.model.InBoxModel;


@Service
@Transactional
public class InBoxService {
	
	@Autowired
	InBoxDao inboxdao;
	
	public  void deleteById(int id) {
		inboxdao.delete(id);
	}
	public  List<InBoxModel> getAllMailsByEmail(String email){
		List<InBoxModel> list=inboxdao.findAllByReciever(email);
		
		if(list.size()>0)
			return list.stream().
					sorted((m1,m2)->-(m1.getDate()).compareTo((m2.getDate())))
					.collect(Collectors.toCollection(ArrayList::new));
		
		
		return list;
	}
	public  InBoxModel getMailById(int id) {
		InBoxModel mail=inboxdao.findById(id);
		
		return mail;
		
	}
	public  void retriveMail(InBoxModel mail) {
			inboxdao.save(mail);
	}

}

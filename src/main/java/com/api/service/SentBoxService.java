package com.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.dao.SentBoxDao;
import com.api.model.SentBoxModel;


@Service
@Transactional
public class SentBoxService {
	@Autowired
	SentBoxDao sentdao;
	
	public  void deleteById(int id) {
		sentdao.delete(id);
		}
	
	public  List<SentBoxModel> getAllMailsByEmail(String email){
		List<SentBoxModel> list=sentdao.findAllBySender(email);
			
		if(list.size()>0)
			return list.stream().
					sorted((m1,m2)->-(m1.getDate()).compareTo((m2.getDate())))
					.collect(Collectors.toCollection(ArrayList::new));
		
		return list;
	}
	
	public  SentBoxModel getMailById(int id) {
		SentBoxModel mail=sentdao.findById(id);
		return mail;
		}

	public  void retriveMail(SentBoxModel mail) {
		sentdao.save(mail);
	}


}

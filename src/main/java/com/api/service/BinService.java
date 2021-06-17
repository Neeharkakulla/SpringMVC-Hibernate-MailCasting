package com.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.dao.BinDao;
import com.api.dao.InBoxDao;
import com.api.dao.SentBoxDao;
import com.api.model.BinModel;
import com.api.model.InBoxModel;
import com.api.model.SentBoxModel;

@Service
@Transactional
public class BinService {
	@Autowired
	InBoxDao inboxdao;
	@Autowired
	SentBoxDao sentboxdao;
	@Autowired
	BinDao bindao;
	
	public  void deleteByBinId(int id) {
		bindao.delete(id);
	}
	public  BinModel getMailById(int id) {
		BinModel mail=bindao.findById(id);
		
		return mail;

	}
	
	public  List<BinModel> getBinMailsByMailId(String email){
		List<BinModel> list=bindao.findAllByUserMail(email);
			if(list.size()>0)
			return list.stream().sorted((m1,m2)->-m1.getDate().compareTo(m2.getDate()))
					.collect(Collectors.toCollection(ArrayList::new));
			return list;
		
	}
	public  void addInboxMailtoBin(int mailid) {
		
		
			
				InBoxModel mail=inboxdao.findById(mailid);
				BinModel binmail=new BinModel();
				
				binmail.setMailid(mail.getId());
				binmail.setMessage(mail.getMessage());
				binmail.setReciever(mail.getReciever());
				binmail.setSender(mail.getSender());
				binmail.setSubject(mail.getSubject());
				binmail.setType("inbox");
				binmail.setUsermail(mail.getReciever());
				binmail.setDate(mail.getDate());
				
				bindao.save(binmail);
				inboxdao.delete(mailid);
					
			
	}
	public  void addSentBoxMailtoBin(int mailid) {
		SentBoxModel mail=sentboxdao.findById(mailid);
		BinModel binmail=new BinModel();
		
		binmail.setMailid(mail.getId());
		binmail.setMessage(mail.getMessage());
		binmail.setReciever(mail.getReciever());
		binmail.setSender(mail.getSender());
		binmail.setSubject(mail.getSubject());
		binmail.setType("sentbox");
		binmail.setUsermail(mail.getSender());
		binmail.setDate(mail.getDate());
		
		bindao.save(binmail);
		sentboxdao.delete(mailid);

	
	}
	public  String retriveFromBin(int id) {
		
		BinModel mail=getMailById(id);
		if(mail.getType().equalsIgnoreCase("inbox")) {
		inboxdao.save(new InBoxModel(mail.getReciever(),mail.getSender()
				,mail.getMessage(),mail.getDate(),mail.getSubject()));
		}
		else if(mail.getType().equalsIgnoreCase("sentbox")) {
			sentboxdao.save(new SentBoxModel(mail.getReciever(),mail.getSender()
					,mail.getMessage(),mail.getDate(),mail.getSubject()));
			}
		
		deleteByBinId(id);
		
		return mail.getType();
	}
}

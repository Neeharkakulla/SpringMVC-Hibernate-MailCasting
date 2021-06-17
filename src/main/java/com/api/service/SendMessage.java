package com.api.service;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.dao.InBoxDao;
import com.api.dao.SentBoxDao;
import com.api.model.InBoxModel;
import com.api.model.SentBoxModel;


@Service
@Transactional
public class SendMessage {
 @Autowired
 InBoxDao inboxdao;
 @Autowired
 SentBoxDao sentboxdao;

public  void sendMsg(SentBoxModel mail){
	java.util.Date sqdate=Calendar.getInstance().getTime();
	java.sql.Timestamp sqlTime=new java.sql.Timestamp(sqdate.getTime());
	
	mail.setDate(sqlTime);
	
	InBoxModel inboxmail=new InBoxModel();
	
	inboxmail.setMessage(mail.getMessage());
	inboxmail.setReciever(mail.getReciever());
	inboxmail.setSender(mail.getSender());
	inboxmail.setSubject(mail.getSubject());
	inboxmail.setDate(sqlTime);
	
	inboxdao.save(inboxmail);
	
	sentboxdao.save(mail);
	
}
}

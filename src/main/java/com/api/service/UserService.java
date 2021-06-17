package com.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.dao.UserDao;
import com.api.model.UserModel;


@Service
@Transactional
public class UserService {

	@Autowired
	UserDao userdao;
	
	
	public  void register(UserModel user){
	 userdao.save(user);
	}
	
	public  UserModel getUserByEmail(String email) {
		return userdao.findByEmail(email);
	}
	
	public  boolean checkLogin(String email,String password){
		
		UserModel dbuser=userdao.findByEmail(email);
		if(dbuser!=null&&dbuser.getPassword().equals(password))
			return true;
		
		return false;
		
	}
	
	public  boolean validatePassword(int id, String password) {
		UserModel dbuser=userdao.findById(id);
		if(dbuser.getPassword().equals(password))
			return true;
		
		return false;
	
	}
	
	public  boolean changePassword(int id, String password) {
		UserModel dbuser=userdao.findById(id);
		dbuser.setPassword(password);
		userdao.save(dbuser);
		return true;
	}
}

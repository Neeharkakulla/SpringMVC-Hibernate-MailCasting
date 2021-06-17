package com.api.dao;

import com.api.model.UserModel;


public interface UserDao{

	UserModel findByEmail(String email);
	UserModel findById(int id);
	void save(UserModel user);

}

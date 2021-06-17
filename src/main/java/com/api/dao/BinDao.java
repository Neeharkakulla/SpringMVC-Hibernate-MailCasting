package com.api.dao;

import java.util.List;

import com.api.model.BinModel;

public interface BinDao {
	
	void save(BinModel mail);

	BinModel findById(int id);

	List<BinModel> findAllByUserMail(String usermail);

	void delete(int id);

}

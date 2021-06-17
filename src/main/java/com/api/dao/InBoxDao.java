package com.api.dao;

import java.util.List;

import com.api.model.InBoxModel;

public interface InBoxDao {

	List<InBoxModel> findAllByReciever(String reciever);

	InBoxModel findById(int id);

	void save(InBoxModel mail);

	void delete(int id);

}

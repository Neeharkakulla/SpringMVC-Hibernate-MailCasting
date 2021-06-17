package com.api.dao;

import java.util.List;

import com.api.model.SentBoxModel;

public interface SentBoxDao {

	void save(SentBoxModel mail);

	SentBoxModel findById(int id);

	List<SentBoxModel> findAllBySender(String sender);

	void delete(int id);

}

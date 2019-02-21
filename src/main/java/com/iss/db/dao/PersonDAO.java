package com.iss.db.dao;

import com.iss.db.entity.Person;
import com.iss.db.entity.PersonAtt;

public interface PersonDAO {
	int deleteByPrimaryKey(String presonId);

	int insert(Person record);

	int insertSelective(Person record);

	Person selectByPrimaryKey(String presonId);

	int updateByPrimaryKeySelective(Person record);

	int updateByPrimaryKey(Person record);

	// ×ÔÐ´²¹³ä²¿·Ö
	PersonAtt selectByUserId(String userId);
}
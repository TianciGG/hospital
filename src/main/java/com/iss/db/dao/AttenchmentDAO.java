package com.iss.db.dao;

import java.util.List;

import com.iss.db.entity.Attenchment;

public interface AttenchmentDAO {
	int deleteByPrimaryKey(String attid);

	int insert(Attenchment record);

	int insertSelective(Attenchment record);

	Attenchment selectByPrimaryKey(String attid);

	int updateByPrimaryKeySelective(Attenchment record);

	int updateByPrimaryKey(Attenchment record);

	// ×ÔÐ´²¹³ä²¿·Ö
	List<Attenchment> getAllAttByTid(String agencyId);
}
package com.iss.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iss.db.entity.Agency;

public interface AgencyDAO {
	int deleteByPrimaryKey(String agencyId);

	int insert(Agency record);

	int insertSelective(Agency record);

	Agency selectByPrimaryKey(String agencyId);

	int updateByPrimaryKeySelective(Agency record);

	int updateByPrimaryKey(Agency record);

	// 自己补充部分
	List<Agency> selectAllAgency(@Param("agencyName") String agencyName);

	List<Agency> getAllAgency();
}
package com.iss.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iss.db.entity.EquipLog;

public interface EquipLogDAO {
	int deleteByPrimaryKey(String logId);

	int insert(EquipLog record);

	int insertSelective(EquipLog record);

	EquipLog selectByPrimaryKey(String logId);

	int updateByPrimaryKeySelective(EquipLog record);

	int updateByPrimaryKey(EquipLog record);

	// ×ÔÐ´²¹³ä²¿·Ö
	List<EquipLog> selectAllEquipLog(@Param("equipName") String equipName, @Param("presonId") String presonId);
}
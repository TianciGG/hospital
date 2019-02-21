package com.iss.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iss.db.entity.DicCode;

public interface DicCodeDAO {
	int deleteByPrimaryKey(String dicId);

	int insert(DicCode record);

	int insertSelective(DicCode record);

	DicCode selectByPrimaryKey(String dicId);

	int updateByPrimaryKeySelective(DicCode record);

	int updateByPrimaryKey(DicCode record);

	// ×ÔÐ´²¹³ä²¿·Ö
	List<DicCode> selectAll();

	List<DicCode> selectDicAll(@Param("dicCodeName") String dicCodeName, @Param("codeType") String codeType);

	List<DicCode> getDataByType(@Param("type") String type);
}
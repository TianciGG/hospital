package com.iss.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iss.db.entity.SysUser;
import com.iss.db.entity.UserManage;

public interface SysUserDAO {
	int deleteByPrimaryKey(String userId);

	int insert(SysUser record);

	int insertSelective(SysUser record);

	SysUser selectByPrimaryKey(String userId);

	int updateByPrimaryKeySelective(SysUser record);

	int updateByPrimaryKey(SysUser record);

	// ²¹³ä×Ô¼ºÐ´
	String selectCountSysUser();

	SysUser loginUser(SysUser user);

	Object checkOriginalPwd(SysUser user);

	List<SysUser> selectAllSysUser(SysUser user);

	List<UserManage> selectUserPerson(@Param("userName") String userName, @Param("status") String status);
}
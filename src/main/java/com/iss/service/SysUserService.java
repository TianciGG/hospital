package com.iss.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iss.db.dao.SysUserDAO;
import com.iss.db.entity.SysUser;
import com.iss.db.entity.UserManage;
import com.iss.serviceifs.ISysUserService;

@Service
public class SysUserService implements ISysUserService {
	@Resource
	private SysUserDAO sysUserDAO;

	@Override
	public boolean addUser(SysUser user) {
		user.setUserId(UUID.randomUUID().toString());
		user.setRole("2");
		return sysUserDAO.insertSelective(user) > 0 ? true : false;
	}

	@Override
	public boolean updateUser(SysUser user) {
		return sysUserDAO.updateByPrimaryKeySelective(user) > 0 ? true : false;
	}

	@Override
	public SysUser loginUser(SysUser user) {
		return sysUserDAO.loginUser(user);
	}

	@Override
	public int userReview(String userId, String personId) {
		return 0;
	}

	@Override
	public List<SysUser> getAllSysUser(SysUser user) {
		List<SysUser> userList = sysUserDAO.selectAllSysUser(user);
		return userList;
	}

	@Override
	public boolean checkOriginalPwd(SysUser user) {
		return sysUserDAO.checkOriginalPwd(user) != null ? true : false;
	}

	@Override
	public String selectCountSysUser() {
		return sysUserDAO.selectCountSysUser();
	}

	@Override
	public SysUser getSysUserById(String userId) {
		return null;
	}

	@Override
	public List<UserManage> selectUserPerson(String userName, String status) {
		return sysUserDAO.selectUserPerson(userName, status);
	}

	@Override
	public int deleteUser(String userId) {
		return sysUserDAO.deleteByPrimaryKey(userId);
	}
}

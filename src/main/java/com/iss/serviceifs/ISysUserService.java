package com.iss.serviceifs;

import java.util.List;

import com.iss.db.entity.SysUser;
import com.iss.db.entity.UserManage;

public interface ISysUserService {
	public String selectCountSysUser();

	public boolean addUser(SysUser user);

	public boolean updateUser(SysUser user);

	public int deleteUser(String userId);

	public List<SysUser> getAllSysUser(SysUser user);

	public SysUser loginUser(SysUser user);

	public int userReview(String userId, String personId);

	public SysUser getSysUserById(String userId);

	public boolean checkOriginalPwd(SysUser user);

	public List<UserManage> selectUserPerson(String userName, String status);
}

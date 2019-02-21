package com.iss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iss.db.entity.Person;
import com.iss.db.entity.SysUser;
import com.iss.db.entity.UserManage;
import com.iss.serviceifs.IPersonService;
import com.iss.serviceifs.ISysUserService;

@Controller
public class SysUserController {
	@Resource
	private ISysUserService sysUserService;
	@Resource
	private IPersonService personService;

	// 注册页面，提交按钮和系统用户管理页面，添加按钮打开页面确定按钮所造成的请求
	@RequestMapping(value = { "addSysUser.do" }, method = { RequestMethod.POST })
	@ResponseBody
	public void addSysUser(SysUser sysUser, Person person, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		sysUser.setStatus("-1");
		sysUser.setUserId(UUID.randomUUID().toString());
		if (sysUserService.addUser(sysUser)) {
			person.setPresonId(UUID.randomUUID().toString());
			person.setUserId(sysUser.getUserId());
			personService.insertSelective(person);
			pw.write("allok");
		} else {
			pw.write("myfail");
		}
		pw.flush();
		pw.close();
	}

	// 系统用户管理页面，页面加载、单击查询按钮。。。时分页显示相关查询数据的请求
	@RequestMapping(value = { "getAllSysUser.do" }, method = { RequestMethod.POST })
	@ResponseBody
	public List<UserManage> getAllSysUser(SysUser sysUser, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		return sysUserService.selectUserPerson(sysUser.getUserName(), sysUser.getStatus());
	}

	// 系统用户管理页面，分页操作项的变更用户的审核状态按钮、权限分配页面的的确定按钮、分页操作项的密码重置按钮所造成的请求
	@RequestMapping(value = { "updateSysUser.do" }, method = { RequestMethod.GET })
	@ResponseBody
	public boolean updateSysUser(SysUser sysUser) {
		if ("-1".equals(sysUser.getStatus())) {
			sysUser.setStatus("1");
		} else if ("1".equals(sysUser.getStatus())) {
			sysUser.setStatus("-1");
		}
		return sysUserService.updateUser(sysUser);
	}

	// 系统用户管理页面，删除页面的提交按钮所造成的请求
	@RequestMapping(value = { "deleteSysUser.do" }, method = { RequestMethod.GET })
	@ResponseBody
	public int deleteSysUser(SysUser sysUser) {
		return sysUserService.deleteUser(sysUser.getUserId());
	}

	// 密码重置页面，原密码文本框，验证输入内容是否正确所造成的请求
	@RequestMapping(value = "checkOriginalPwd.do")
	@ResponseBody
	public void checkOriginalPwd(SysUser sysUser, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		if (sysUserService.checkOriginalPwd(sysUser)) {
			pw.write("allok");
		} else {
			pw.write("myfail");
		}
		pw.flush();
		pw.close();
	}
}

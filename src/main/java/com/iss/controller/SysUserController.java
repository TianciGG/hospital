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

	// ע��ҳ�棬�ύ��ť��ϵͳ�û�����ҳ�棬��Ӱ�ť��ҳ��ȷ����ť����ɵ�����
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

	// ϵͳ�û�����ҳ�棬ҳ����ء�������ѯ��ť������ʱ��ҳ��ʾ��ز�ѯ���ݵ�����
	@RequestMapping(value = { "getAllSysUser.do" }, method = { RequestMethod.POST })
	@ResponseBody
	public List<UserManage> getAllSysUser(SysUser sysUser, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		return sysUserService.selectUserPerson(sysUser.getUserName(), sysUser.getStatus());
	}

	// ϵͳ�û�����ҳ�棬��ҳ������ı���û������״̬��ť��Ȩ�޷���ҳ��ĵ�ȷ����ť����ҳ��������������ð�ť����ɵ�����
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

	// ϵͳ�û�����ҳ�棬ɾ��ҳ����ύ��ť����ɵ�����
	@RequestMapping(value = { "deleteSysUser.do" }, method = { RequestMethod.GET })
	@ResponseBody
	public int deleteSysUser(SysUser sysUser) {
		return sysUserService.deleteUser(sysUser.getUserId());
	}

	// ��������ҳ�棬ԭ�����ı�����֤���������Ƿ���ȷ����ɵ�����
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

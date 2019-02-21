package com.iss.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iss.db.entity.PersonAtt;
import com.iss.db.entity.SysUser;
import com.iss.serviceifs.IPersonService;

@Controller
public class JumpController {

	@Resource
	private IPersonService personService;

	// index.jsp��ת����¼ҳ�������
	@RequestMapping(value = "goToMyLoginPage.do", method = RequestMethod.GET)
	public String toIndex() {
		return "mylogin";
	}

	// ��¼ҳ����ת��ע��ҳ�������
	@RequestMapping(value = "goToMyRegPage.do", method = RequestMethod.GET)
	public String goToMyRegPage() {
		return "myreg";
	}

	// ��¼ҳ���¼�ɹ���ת����̨��ҳ������
	@RequestMapping(value = "goToMainPage.do", method = RequestMethod.GET)
	public String goToMainPage() {
		return "main";
	}

	// ��ת����������ҳ�������
	@RequestMapping(value = "goToPersonalCenterPage.do", method = RequestMethod.GET)
	public String goToPersonalCenter(HttpServletRequest request) {
		SysUser myUser = (SysUser) request.getSession().getAttribute("myuser");
		PersonAtt myPerson = personService.selectByUserId(myUser.getUserId());
		request.getSession().setAttribute("myperson", myPerson);
		return "personalCenter";
	}

	// ��ת�������޸�ҳ�������
	@RequestMapping(value = "goToChangePwdPage.do", method = RequestMethod.GET)
	public String goToChangePwd() {
		return "changePwd";
	}

	// �˳���¼����ҳ������
	@RequestMapping(value = "myQuit.do", method = RequestMethod.GET)
	public String myQuit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		return "mylogin";
	}

	// ��ת����Ӧ�̹���ҳ�������
	@RequestMapping(value = "goToSupplierManagePage.do", method = RequestMethod.GET)
	public String goToSupplierManage() {
		return "supplierManage";
	}

	// ��ת�������ֵ����ҳ�������
	@RequestMapping(value = "goToDicListPage.do", method = RequestMethod.GET)
	public String goToDicList() {
		return "dicList";
	}

	// ��ת���û�����ҳ�������
	@RequestMapping(value = "goToUserManagePage.do", method = RequestMethod.GET)
	public String goToUserManage() {
		return "userManage";
	}

	// ��ת���������豸ͳ��ҳ�������
	@RequestMapping(value = "goToStatisticsDeptEqptPage.do", method = RequestMethod.GET)
	public String goToStatisticsDeptEqpt() {
		return "statisticsDeptEqpt";
	}

	// ��ת���豸��־����ҳ�������
	@RequestMapping(value = "goToEquipLogManagePage.do", method = RequestMethod.GET)
	public String goToEquipLogManage() {
		return "equipLogManage";
	}

	// ��ת���豸Ԥ������ҳ�������
	@RequestMapping(value = "goToEquipManagePage.do", method = RequestMethod.GET)
	public String goToEquipManage() {
		return "equipManage";
	}
}

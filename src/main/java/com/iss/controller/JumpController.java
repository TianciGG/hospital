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

	// index.jsp跳转到登录页面的请求
	@RequestMapping(value = "goToMyLoginPage.do", method = RequestMethod.GET)
	public String toIndex() {
		return "mylogin";
	}

	// 登录页面跳转到注册页面的请求
	@RequestMapping(value = "goToMyRegPage.do", method = RequestMethod.GET)
	public String goToMyRegPage() {
		return "myreg";
	}

	// 登录页面登录成功跳转到后台首页的请求
	@RequestMapping(value = "goToMainPage.do", method = RequestMethod.GET)
	public String goToMainPage() {
		return "main";
	}

	// 跳转到个人中心页面的请求
	@RequestMapping(value = "goToPersonalCenterPage.do", method = RequestMethod.GET)
	public String goToPersonalCenter(HttpServletRequest request) {
		SysUser myUser = (SysUser) request.getSession().getAttribute("myuser");
		PersonAtt myPerson = personService.selectByUserId(myUser.getUserId());
		request.getSession().setAttribute("myperson", myPerson);
		return "personalCenter";
	}

	// 跳转到密码修改页面的请求
	@RequestMapping(value = "goToChangePwdPage.do", method = RequestMethod.GET)
	public String goToChangePwd() {
		return "changePwd";
	}

	// 退出登录到首页的请求
	@RequestMapping(value = "myQuit.do", method = RequestMethod.GET)
	public String myQuit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		return "mylogin";
	}

	// 跳转到供应商管理页面的请求
	@RequestMapping(value = "goToSupplierManagePage.do", method = RequestMethod.GET)
	public String goToSupplierManage() {
		return "supplierManage";
	}

	// 跳转到数据字典管理页面的请求
	@RequestMapping(value = "goToDicListPage.do", method = RequestMethod.GET)
	public String goToDicList() {
		return "dicList";
	}

	// 跳转到用户管理页面的请求
	@RequestMapping(value = "goToUserManagePage.do", method = RequestMethod.GET)
	public String goToUserManage() {
		return "userManage";
	}

	// 跳转到各科室设备统计页面的请求
	@RequestMapping(value = "goToStatisticsDeptEqptPage.do", method = RequestMethod.GET)
	public String goToStatisticsDeptEqpt() {
		return "statisticsDeptEqpt";
	}

	// 跳转到设备日志管理页面的请求
	@RequestMapping(value = "goToEquipLogManagePage.do", method = RequestMethod.GET)
	public String goToEquipLogManage() {
		return "equipLogManage";
	}

	// 跳转到设备预警管理页面的请求
	@RequestMapping(value = "goToEquipManagePage.do", method = RequestMethod.GET)
	public String goToEquipManage() {
		return "equipManage";
	}
}

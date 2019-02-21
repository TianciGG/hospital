package com.iss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iss.db.entity.DicCode;
import com.iss.service.DicService;

@Controller
@RequestMapping("/logic")
public class DicController {
	@Autowired
	private DicService dicService;

	// 数据字典管理页面，给数据类型下拉列表赋值的请求
	@RequestMapping("/selectDicType.do")
	@ResponseBody
	public List<DicCode> selectDicType() {
		return dicService.selectDicType();
	}

	// 数据字典管理页面，页面加载、单击查询按钮。。。时分页显示相关查询数据的请求
	@RequestMapping(value = "/getAllDic.do")
	@ResponseBody
	public List<DicCode> getAllDic(DicCode dicCode, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		return dicService.selectDicAll(dicCode);
	}

	// 数据字典管理页面，分页操作项的编辑图标打开的页面大的初始值请求
	@RequestMapping(value = "/getOneDicCode.do")
	@ResponseBody
	public DicCode getOneDicCode(DicCode dicCode) {
		return dicService.selectByPrimaryKey(dicCode.getDicId());
	}

	// 数据字典管理页面，添加按钮和分页操作项的编辑图标打开页面的确定按钮产生的请求
	@RequestMapping(value = "/addOrUpdate.do")
	@ResponseBody
	public void addOrUpdate(DicCode dicCode, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		if (!"".equals(dicCode.getDicId())) {
			pw.write(dicService.updateByPrimaryKeySelective(dicCode) == true ? "allok" : "myfail");
		} else {
			dicCode.setDicId(UUID.randomUUID().toString());
			dicCode.setDicCode(UUID.randomUUID().toString());
			pw.write(dicService.insertSelective(dicCode) == true ? "allok" : "myfail");
		}
		pw.flush();
		pw.close();
	}

	// 数据字典管理页面，分页操作项的删除图标打开页面的提交按钮产生的请求
	@RequestMapping(value = "/deleteOneDicCode.do")
	@ResponseBody
	public void deleteOneDicCode(DicCode dicCode, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(dicService.deleteByPrimaryKey(dicCode.getDicId()) == true ? "allok" : "myfail");
		pw.flush();
		pw.close();
	}

	// 系统用户管理页面，权限分配页面获取当前活动行的相关值的请求
	@RequestMapping(value = { "/getDropDown.do" }, method = { RequestMethod.POST })
	@ResponseBody
	public List<DicCode> checkOriginalPwd(DicCode dicCode, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		return dicService.getDataByType(dicCode.getType());
	}
}

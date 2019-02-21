package com.iss.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iss.db.entity.Agency;
import com.iss.db.entity.Attenchment;
import com.iss.service.AgencyService;
import com.iss.service.UploadService;

@Controller
@RequestMapping("/logic")
public class AgencyController {
	@Autowired
	private AgencyService agencyService;
	@Autowired
	private UploadService uploadService;

	// 供应商管理页面，页面加载、单击查询按钮。。。时分页显示相关查询数据的请求
	@RequestMapping(value = "/getAllAgency.do")
	@ResponseBody
	public List<Agency> getAllAgency(Agency agency, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		return agencyService.selectAllAgency(agency.getAgencyName());
	}

	// 供应商管理页面，添加按钮和分页操作项的编辑图标打开页面的确定按钮产生的请求
	@RequestMapping(value = "/addOrUpdateAgency.do")
	@ResponseBody
	public String addOrUpdate(Agency agency, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		if (agency.getAgencyId() != null && !"".equals(agency.getAgencyId())) {
			return agencyService.updateByPrimaryKeySelective(agency) > 0 ? "allok" : "myfail";
		} else {
			agency.setAgencyId(UUID.randomUUID().toString());
			return agencyService.insertSelective(agency) > 0 ? "allok" : "myfail";
		}
	}

	// 供应商管理页面，分页操作项的编辑图标所造成的请求
	@RequestMapping(value = "/getOneAgency.do")
	@ResponseBody
	public Agency getOneAgency(Agency agency, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		return agencyService.selectByPrimaryKey(agency.getAgencyId());
	}

	// 供应商管理页面，分页操作项的删除图标打开页面的提交按钮所造成的请求
	@RequestMapping(value = "/deleteOneAgency.do")
	@ResponseBody
	public String deleteOneAgency(Agency agency, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		return agencyService.deleteByPrimaryKey(agency.getAgencyId()) > 0 ? "allok" : "myfail";
	}

	// 供应商管理页面，分页操作项的附件列表图标打开页面所造成的请求
	@RequestMapping(value = "/getAllAtt.do")
	@ResponseBody
	public List<Attenchment> getAllAtt(Agency agency, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		return uploadService.getAllAtt(agency.getAgencyId());
	}

	// 设备预警管理页面，用于为供应商下拉列表提供支持所造成的请求
	@RequestMapping(value = "/getAgencyDropDown.do")
	@ResponseBody
	public List<Agency> getAgencyDropDown() {
		return agencyService.getAllAgency();
	}

}

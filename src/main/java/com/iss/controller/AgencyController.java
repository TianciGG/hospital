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

	// ��Ӧ�̹���ҳ�棬ҳ����ء�������ѯ��ť������ʱ��ҳ��ʾ��ز�ѯ���ݵ�����
	@RequestMapping(value = "/getAllAgency.do")
	@ResponseBody
	public List<Agency> getAllAgency(Agency agency, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		return agencyService.selectAllAgency(agency.getAgencyName());
	}

	// ��Ӧ�̹���ҳ�棬��Ӱ�ť�ͷ�ҳ������ı༭ͼ���ҳ���ȷ����ť����������
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

	// ��Ӧ�̹���ҳ�棬��ҳ������ı༭ͼ������ɵ�����
	@RequestMapping(value = "/getOneAgency.do")
	@ResponseBody
	public Agency getOneAgency(Agency agency, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		return agencyService.selectByPrimaryKey(agency.getAgencyId());
	}

	// ��Ӧ�̹���ҳ�棬��ҳ�������ɾ��ͼ���ҳ����ύ��ť����ɵ�����
	@RequestMapping(value = "/deleteOneAgency.do")
	@ResponseBody
	public String deleteOneAgency(Agency agency, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		return agencyService.deleteByPrimaryKey(agency.getAgencyId()) > 0 ? "allok" : "myfail";
	}

	// ��Ӧ�̹���ҳ�棬��ҳ������ĸ����б�ͼ���ҳ������ɵ�����
	@RequestMapping(value = "/getAllAtt.do")
	@ResponseBody
	public List<Attenchment> getAllAtt(Agency agency, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		return uploadService.getAllAtt(agency.getAgencyId());
	}

	// �豸Ԥ������ҳ�棬����Ϊ��Ӧ�������б��ṩ֧������ɵ�����
	@RequestMapping(value = "/getAgencyDropDown.do")
	@ResponseBody
	public List<Agency> getAgencyDropDown() {
		return agencyService.getAllAgency();
	}

}

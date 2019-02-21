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

	// �����ֵ����ҳ�棬���������������б�ֵ������
	@RequestMapping("/selectDicType.do")
	@ResponseBody
	public List<DicCode> selectDicType() {
		return dicService.selectDicType();
	}

	// �����ֵ����ҳ�棬ҳ����ء�������ѯ��ť������ʱ��ҳ��ʾ��ز�ѯ���ݵ�����
	@RequestMapping(value = "/getAllDic.do")
	@ResponseBody
	public List<DicCode> getAllDic(DicCode dicCode, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		return dicService.selectDicAll(dicCode);
	}

	// �����ֵ����ҳ�棬��ҳ������ı༭ͼ��򿪵�ҳ���ĳ�ʼֵ����
	@RequestMapping(value = "/getOneDicCode.do")
	@ResponseBody
	public DicCode getOneDicCode(DicCode dicCode) {
		return dicService.selectByPrimaryKey(dicCode.getDicId());
	}

	// �����ֵ����ҳ�棬��Ӱ�ť�ͷ�ҳ������ı༭ͼ���ҳ���ȷ����ť����������
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

	// �����ֵ����ҳ�棬��ҳ�������ɾ��ͼ���ҳ����ύ��ť����������
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

	// ϵͳ�û�����ҳ�棬Ȩ�޷���ҳ���ȡ��ǰ��е����ֵ������
	@RequestMapping(value = { "/getDropDown.do" }, method = { RequestMethod.POST })
	@ResponseBody
	public List<DicCode> checkOriginalPwd(DicCode dicCode, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		return dicService.getDataByType(dicCode.getType());
	}
}

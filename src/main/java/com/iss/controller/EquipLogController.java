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
import org.springframework.web.bind.annotation.ResponseBody;

import com.iss.db.entity.Device;
import com.iss.db.entity.EquipLog;
import com.iss.service.DeviceService;
import com.iss.service.EquipLogService;

@Controller
@RequestMapping("/logic")
public class EquipLogController {
	@Autowired
	private EquipLogService equipLogService;
	@Autowired
	private DeviceService deviceService;

	// 设备日志管理页面，分页显示相关查询数据所造成的请求
	@RequestMapping(value = "/getAllLog.do")
	@ResponseBody
	public List<EquipLog> getAllLogJSON(EquipLog equipLog, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		return equipLogService.selectAllEquipLog(equipLog.getEquipName(), equipLog.getPresonId());
	}

	// 设备日志管理页面，分页操作项的编辑内容按钮所造成的请求
	@RequestMapping(value = "/getOneLog.do")
	@ResponseBody
	public EquipLog getOneLog(EquipLog equipLog, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		return equipLogService.selectByPrimaryKey(equipLog.getLogId());
	}

	// 设备日志管理页面，分页操作项的编辑内容按钮打开页面的确定按钮所造成的请求
	@RequestMapping(value = "/addOrUpdateLog.do")
	@ResponseBody
	public void addOrUpdateLogJSON(EquipLog equipLog, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		Device device = new Device();
		device.setEquipId(equipLog.getEquipId());
		device.setCheckDate(equipLog.getLogAddtime());
		if (!"".equals(equipLog.getLogId()) && equipLog.getLogId() != null) {
			pw.write((equipLogService.updateByPrimaryKeySelective(equipLog) > 0
					&& deviceService.updateByPrimaryKeySelective(device) > 0) ? "allok" : "myfail");
		} else {
			equipLog.setLogId(UUID.randomUUID().toString());
			pw.write((equipLogService.insertSelective(equipLog) > 0
					&& deviceService.updateByPrimaryKeySelective(device) > 0) ? "allok" : "myfail");
		}
		pw.flush();
		pw.close();
	}

	// 设备日志管理页面，分页操作项的删除图标打开页面的提交按钮所造成的请求
	@RequestMapping(value = "/deleteOneLog.do")
	@ResponseBody
	public void deleteOneLogJSON(EquipLog equipLog, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(equipLogService.deleteByPrimaryKey(equipLog.getLogId()) > 0 ? "allok" : "myfail");
		pw.flush();
		pw.close();
	}
}

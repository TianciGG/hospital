package com.iss.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.iss.db.entity.AgencyAtt;
import com.iss.db.entity.Device;
import com.iss.db.entity.DeviceAtt;
import com.iss.service.DeviceService;
import com.iss.service.ExcelService;
import com.iss.util.DateUtil;
import com.iss.util.ExcelUtil;

@Controller
@RequestMapping("/logic")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ExcelService excelService;

	// �������豸ʹ�����ͳ��ҳ�棬ȡ��״ͼ��������ɵ�����
	@RequestMapping(value = "/getCharColAlarmNum.do")
	public void getCharColAlarmNum(Device device, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(deviceService.getCharColAlarmNum() + "," + deviceService.getCharColAlarmNumReady());
	}

	// �������豸ʹ�����ͳ��ҳ�棬ȡ��״ͼ��������ɵ�����
	@RequestMapping(value = "/getChartPieDeviceNum.do")
	public void getChartPieDeviceNum(Device device, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(deviceService.getChartPieDeviceNum());
	}

	// �豸Ԥ������ҳ�棬��ҳ��ʾ��ز�ѯ��������ɵ�����
	@RequestMapping(value = "/getAllDevices.do")
	@ResponseBody
	public List<Device> getAllDevices(Device device, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		return deviceService.selectAllDevices(device);
	}

	// �豸Ԥ������ҳ��,��Ӱ�ť��ҳ��ȷ����ť����ɵ�����
	@RequestMapping(value = "/addDevice.do")
	@ResponseBody
	public int addDevice(Device device) {
		if ("".equals(device.getEquipId()) || device.getEquipId() == null) {
			device.setCheckDate(DateUtil.dateToStringHHmm(new Date()));
			device.setEquipId(UUID.randomUUID().toString());
			return deviceService.insertSelective(device);
		}
		return deviceService.updateByPrimaryKeySelective(device);
	}

	// �豸Ԥ������ҳ��,����Excel��ť���¼�
	@RequestMapping(value = "/getExcelDevice.do", method = RequestMethod.GET)
	public void getExcelDevice(Device device, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<Device> deviceList = deviceService.selectAllDevices(device);
		ExcelUtil excelUtil = new ExcelUtil();
		excelUtil.exportExcel(request, response, deviceList);
	}

	// �豸Ԥ������ҳ��,����Excel��ť��ҳ�浼�밴ť����ɵ�����
	@RequestMapping(value = "/importExcelDevice.do")
	@ResponseBody
	public void importExcelDevice(@RequestParam(value = "myExcelFile", required = false) MultipartFile mfile,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Device> tempList = excelService.importExcel(mfile);
		if (deviceService.importDataByExcel(tempList)) {
			response.getWriter().write("myok");
		} else {
			response.getWriter().write("myfail");
		}
	}

	// �豸Ԥ������ҳ�棬��ҳ������༭���ݰ�ť����ɵ�����
	@RequestMapping(value = "/getOneDevice.do")
	@ResponseBody
	public DeviceAtt getOneDevice(Device device, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		DeviceAtt deviceAtt = deviceService.selectOneDeviceForUpdate(device.getEquipId());
		response.setCharacterEncoding("UTF-8");
		return deviceAtt;
	}

	// �豸Ԥ������ҳ�棬��ҳ��������豸��ť��ҳ���ύ��ť����ɵ�����
	@RequestMapping(value = "/updateDevice.do")
	@ResponseBody
	public int updateDevice(Device device) {
		return deviceService.updateByPrimaryKeySelective(device);
	}

	// �豸Ԥ������ҳ�棬��ҳ������ɾ��ͼ���ҳ���ύ��ť����ɵ�����
	@RequestMapping(value = "/deleteDevice.do")
	@ResponseBody
	public int deleteDevice(Device device) {
		return deviceService.deleteByPrimaryKey(device.getEquipId());
	}

	// �豸Ԥ������ҳ�棬��ҳ��������ϸ�����ť����ɵ�����
	@RequestMapping(value = "/getDeviceDetail.do")
	@ResponseBody
	public AgencyAtt getDeviceDetail(Device device, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		AgencyAtt agencyAtt = deviceService.getDeviceDetail(device.getEquipId());
		response.setCharacterEncoding("UTF-8");
		return agencyAtt;
	}
}

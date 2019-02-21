package com.iss.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.db.dao.DeviceDAO;
import com.iss.db.dao.DicCodeDAO;
import com.iss.db.entity.AgencyAtt;
import com.iss.db.entity.ChartObject;
import com.iss.db.entity.Device;
import com.iss.db.entity.DeviceAtt;
import com.iss.db.entity.DicCode;

@Service
public class DeviceService {
	@Autowired
	private DeviceDAO deviceDao;
	@Autowired
	private DicCodeDAO dicCodeDao;

	public String getCharColAlarmNum() {
		List<ChartObject> tempList = deviceDao.getCharColAlarmNum();
		StringBuffer buffer = new StringBuffer("");
		buffer.append("[{name: '待检测设备数量', data: [");
		for (ChartObject chart : tempList) {
			buffer.append(chart.getCnum() + ",");
		}
		String result = buffer.substring(0, buffer.length() - 1) + "], stack: 'female' }";
		return result;
	}

	public String getCharColAlarmNumReady() {
		List<ChartObject> tempList = deviceDao.getCharColAlarmNumReady();
		StringBuffer buffer = new StringBuffer("{name: '正常使用设备数量', data: [");
		StringBuffer bufferType = new StringBuffer("[");
		for (ChartObject chart : tempList) {
			bufferType.append("'" + chart.getCtype() + "',");
			buffer.append(chart.getCnum() + ",");
		}
		String result = buffer.substring(0, buffer.length() - 1) + "], stack: 'female' }] | "
				+ bufferType.substring(0, bufferType.length() - 1) + "]";
		return result;
	}

	public String getChartPieDeviceNum() {
		StringBuffer buffer = new StringBuffer("[{name: '设备数量',data: [");
		List<ChartObject> deviceList = deviceDao.getChartPieDeviceNum();
		for (ChartObject chart : deviceList) {
			buffer.append("['" + chart.getCtype() + "'," + chart.getCnum() + "],");
		}
		String result = buffer.substring(0, buffer.length() - 1) + "] }]";
		return result;
	}

	public int updateByPrimaryKeySelective(Device device) {
		return deviceDao.updateByPrimaryKeySelective(device);
	}

	public List<Device> selectAllDevices(Device device) {
		return deviceDao.selectAllDevices(device.getDicCode(), device.getDeviceName());
	}

	public int insertSelective(Device device) {
		return deviceDao.insertSelective(device);
	}

	public DeviceAtt selectOneDeviceForUpdate(String equipId) {
		return deviceDao.selectOneDeviceForUpdate(equipId);
	}

	public AgencyAtt getDeviceDetail(String equipId) {
		return deviceDao.getDeviceDetail(equipId);
	}

	public boolean importDataByExcel(List<Device> tempList) {
		try {
			List keshiList = dicCodeDao.getDataByType("科室");
			List supportList = dicCodeDao.getDataByType("供应商");
			List newDataList = new ArrayList();
			// 替换 科室 code
			for (int i = 0; i < tempList.size(); i++) {
				Device device = (Device) tempList.get(i);
				for (int j = 0; j < keshiList.size(); j++) {
					DicCode dicCode = (DicCode) keshiList.get(j);
					if (device.getDicCode().equals(dicCode.getName())) {
						device.setDicCode(dicCode.getDicCode());
					}
				}
				for (int j = 0; j < supportList.size(); j++) {
					DicCode dicCode = (DicCode) supportList.get(j);
					if (device.getAgencyId().equals(dicCode.getName())) {
						device.setAgencyId(dicCode.getDicCode());
					}
				}
				newDataList.add(device);
			}
			for (int i = 0; i < newDataList.size(); i++) {
				Device device = (Device) newDataList.get(i);
				if (getDevcieByName(device.getDeviceName()) != null) {
					deviceDao.updateByPrimaryKeySelective(device);
				} else {
					device.setEquipId(UUID.randomUUID().toString());
					deviceDao.insertSelective(device);
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Device getDevcieByName(String dname) {
		return deviceDao.getDevcieByName(dname);
	}

	public int deleteByPrimaryKey(String equipId) {
		return deviceDao.deleteByPrimaryKey(equipId);
	}
}

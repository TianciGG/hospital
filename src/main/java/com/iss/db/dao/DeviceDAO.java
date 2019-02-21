package com.iss.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iss.db.entity.AgencyAtt;
import com.iss.db.entity.ChartObject;
import com.iss.db.entity.Device;
import com.iss.db.entity.DeviceAtt;

public interface DeviceDAO {
	int deleteByPrimaryKey(String equipId);

	int insert(Device record);

	int insertSelective(Device record);

	Device selectByPrimaryKey(String equipId);

	int updateByPrimaryKeySelective(Device record);

	int updateByPrimaryKey(Device record);

	// ×ÔÐ´²¹³ä²¿·Ö
	List<ChartObject> getCharColAlarmNum();

	List<ChartObject> getCharColAlarmNumReady();

	List<ChartObject> getChartPieDeviceNum();

	List<Device> selectAllDevices(@Param("dicCode") String dicCode, @Param("deviceName") String deviceName);

	DeviceAtt selectOneDeviceForUpdate(String equipId);

	AgencyAtt getDeviceDetail(String equipId);

	Device getDevcieByName(String dname);
}
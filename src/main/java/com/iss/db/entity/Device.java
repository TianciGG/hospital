package com.iss.db.entity;

public class Device {
	private String equipId;

	private String agencyId;

	private String dicCode;

	private String deviceName;

	private String dtype;

	private String factory;

	private String dnumber;

	private String buyDate;

	private String checkDate;

	private String sid;

	private String leftDay;

	private String checkCircle;

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId == null ? null : equipId.trim();
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId == null ? null : agencyId.trim();
	}

	public String getDicCode() {
		return dicCode;
	}

	public void setDicCode(String dicCode) {
		this.dicCode = dicCode == null ? null : dicCode.trim();
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName == null ? null : deviceName.trim();
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype == null ? null : dtype.trim();
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory == null ? null : factory.trim();
	}

	public String getDnumber() {
		return dnumber;
	}

	public void setDnumber(String dnumber) {
		this.dnumber = dnumber == null ? null : dnumber.trim();
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate == null ? null : buyDate.trim();
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate == null ? null : checkDate.trim();
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid == null ? null : sid.trim();
	}

	public String getLeftDay() {
		return leftDay;
	}

	public void setLeftDay(String leftDay) {
		this.leftDay = leftDay == null ? null : leftDay.trim();
	}

	public String getCheckCircle() {
		return checkCircle;
	}

	public void setCheckCircle(String checkCircle) {
		this.checkCircle = checkCircle == null ? null : checkCircle.trim();
	}
}
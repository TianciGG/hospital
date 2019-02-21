package com.iss.db.entity;

public class EquipLog {
	private String logId;

	private String presonId;

	private String equipId;

	private String logAddtime;

	private String fixContent;

	private String equipName;

	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId == null ? null : logId.trim();
	}

	public String getPresonId() {
		return presonId;
	}

	public void setPresonId(String presonId) {
		this.presonId = presonId == null ? null : presonId.trim();
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId == null ? null : equipId.trim();
	}

	public String getLogAddtime() {
		return logAddtime;
	}

	public void setLogAddtime(String logAddtime) {
		this.logAddtime = logAddtime == null ? null : logAddtime.trim();
	}

	public String getFixContent() {
		return fixContent;
	}

	public void setFixContent(String fixContent) {
		this.fixContent = fixContent == null ? null : fixContent.trim();
	}
}
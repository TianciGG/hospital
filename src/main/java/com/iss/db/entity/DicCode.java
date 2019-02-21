package com.iss.db.entity;

public class DicCode {
	private String dicId;

	private String dicCode;

	private String name;

	private String type;

	public String getDicId() {
		return dicId;
	}

	public void setDicId(String dicId) {
		this.dicId = dicId == null ? null : dicId.trim();
	}

	public String getDicCode() {
		return dicCode;
	}

	public void setDicCode(String dicCode) {
		this.dicCode = dicCode == null ? null : dicCode.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}
}
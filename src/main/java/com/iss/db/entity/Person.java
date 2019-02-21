package com.iss.db.entity;

public class Person {
	private String presonId;

	private String userId;

	private String dicId;

	private String gender;

	private String mobile;

	private String email;

	private String cname;

	public String getPresonId() {
		return presonId;
	}

	public void setPresonId(String presonId) {
		this.presonId = presonId == null ? null : presonId.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getDicId() {
		return dicId;
	}

	public void setDicId(String dicId) {
		this.dicId = dicId == null ? null : dicId.trim();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender == null ? null : gender.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname == null ? null : cname.trim();
	}
}
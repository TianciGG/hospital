package com.iss.db.entity;

public class Agency {
	private String agencyId;

	private String license;

	private String permitNo;

	private String agencyName;

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId == null ? null : agencyId.trim();
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license == null ? null : license.trim();
	}

	public String getPermitNo() {
		return permitNo;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo == null ? null : permitNo.trim();
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName == null ? null : agencyName.trim();
	}
}
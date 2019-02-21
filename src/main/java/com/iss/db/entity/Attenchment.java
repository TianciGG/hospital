package com.iss.db.entity;

public class Attenchment {
	private String attid;

	private String id;

	private String attname;

	private String type;

	private String atturl;

	public String getAttid() {
		return attid;
	}

	public void setAttid(String attid) {
		this.attid = attid == null ? null : attid.trim();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getAttname() {
		return attname;
	}

	public void setAttname(String attname) {
		this.attname = attname == null ? null : attname.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getAtturl() {
		return atturl;
	}

	public void setAtturl(String atturl) {
		this.atturl = atturl == null ? null : atturl.trim();
	}
}
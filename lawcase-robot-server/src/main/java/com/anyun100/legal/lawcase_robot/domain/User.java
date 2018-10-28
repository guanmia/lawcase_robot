package com.anyun100.legal.lawcase_robot.domain;


import java.sql.Timestamp;
public class User {
	
	private Long uid;
	private String openid;
	private Timestamp reg_time;
	public User(Long uid, String openid, Timestamp reg_time) {
		this.uid = uid;
		this.openid = openid;
		this.reg_time = reg_time;
	}
	public Long getUid() {
		return uid;
	}
	public String getOpenid() {
		return openid;
	}
	public Timestamp getReg_time() {
		return reg_time;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public void setReg_time(Timestamp reg_time) {
		this.reg_time = reg_time;
	}

}

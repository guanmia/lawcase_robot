package com.anyun100.legal.lawcase_robot.domain;

import java.util.List;

public class UserCurrentLegalCase {

	private String openid;
	private List<CurrentLegalCase> currentLegalCases;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public List<CurrentLegalCase> getCurrentLegalCases() {
		return currentLegalCases;
	}
	public void setCurrentLegalCases(List<CurrentLegalCase> currentLegalCases) {
		this.currentLegalCases = currentLegalCases;
	}
}

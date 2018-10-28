package com.anyun100.legal.lawcase_robot.domain.dto;

import java.io.Serializable;


public class OldCaseDto implements Serializable, Comparable<OldCaseDto> {
	
	private Long oldcase_id;
	private String case_num;
	private String case_date;
	private String court;
	
	
	public OldCaseDto(Long oldcase_id, String case_num, String case_date, String court) {
		this.oldcase_id = oldcase_id;
		this.case_num = case_num;
		this.case_date = case_date;
		this.court = court;
	}


	public Long getOldcase_id() {
		return oldcase_id;
	}


	public String getCase_num() {
		return case_num;
	}


	public String getCase_date() {
		return case_date;
	}


	public String getCourt() {
		return court;
	}


	public void setOldcase_id(Long oldcase_id) {
		this.oldcase_id = oldcase_id;
	}


	public void setCase_num(String case_num) {
		this.case_num = case_num;
	}


	public void setCase_date(String case_date) {
		this.case_date = case_date;
	}


	public void setCourt(String court) {
		this.court = court;
	}


	public int compareTo(OldCaseDto other) {
		return this.oldcase_id.compareTo(other.oldcase_id);
	}

}

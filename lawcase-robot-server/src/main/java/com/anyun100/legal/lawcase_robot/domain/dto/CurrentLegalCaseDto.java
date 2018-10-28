package com.anyun100.legal.lawcase_robot.domain.dto;

import java.io.Serializable;

import com.anyun100.legal.lawcase_robot.util.DateTimeUtil;

public class CurrentLegalCaseDto implements Comparable<CurrentLegalCaseDto>, Serializable {

	private String case_id;
	private String case_num;
	private String case_content;
	private String court_datetime;

	public CurrentLegalCaseDto(String case_id, String case_num, String case_content, String court_datetime) {
		this.case_id = case_id;
		this.case_num = case_num;
		this.case_content = case_content;
		this.court_datetime = court_datetime;
	}

	public String getCase_content() {
		return case_content;
	}

	public void setCase_content(String case_content) {
		this.case_content = case_content;
	}

	public String getCase_id() {
		return case_id;
	}

	public void setCase_id(String case_id) {
		this.case_id = case_id;
	}

	public String getCourt_datetime() {
		return court_datetime;
	}

	public void setCourt_datetime(String court_datetime) {
		this.court_datetime = court_datetime;
	}

	public String getCase_num() {
		return case_num;
	}

	public void setCase_num(String case_num) {
		this.case_num = case_num;
	}

	public int compareTo(CurrentLegalCaseDto other) {
		if (DateTimeUtil.parseLocalDateTime(court_datetime)
				.isBefore(DateTimeUtil.parseLocalDateTime(other.court_datetime))) {
			return 1;
		} else if (DateTimeUtil.parseLocalDateTime(court_datetime)
				.isAfter(DateTimeUtil.parseLocalDateTime(other.court_datetime))) {
			return -1;
		}
		return 0;
	}

}

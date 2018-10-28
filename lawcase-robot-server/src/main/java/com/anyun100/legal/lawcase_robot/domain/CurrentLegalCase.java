package com.anyun100.legal.lawcase_robot.domain;

import java.io.Serializable;

import com.anyun100.legal.lawcase_robot.util.DateTimeUtil;

public class CurrentLegalCase implements Comparable<CurrentLegalCase>, Serializable {

	private String case_id;
	
	
	private String court_datetime;
	
	private String case_num;
	private String case_name;
	private String case_content;
	private String content_file_name;

	public String getCase_content() {
		return case_content;
	}

	public void setCase_content(String case_content) {
		this.case_content = case_content;
	}

	public String getContent_file_name() {
		return content_file_name;
	}

	public void setContent_file_name(String content_file_name) {
		this.content_file_name = content_file_name;
	}

	public String getCaseId() {
		return case_id;
	}

	public void setCaseId(String case_id) {
		this.case_id = case_id;
	}

	public String getCourt_datetime() {
		return court_datetime;
	}

	public void setCourt_date(String court_datetime) {
		this.court_datetime = court_datetime;
	}

	public String getCase_num() {
		return case_num;
	}

	public void setCase_num(String case_num) {
		this.case_num = case_num;
	}

	public String getCase_name() {
		return (case_name == null ? "" : case_name);
	}

	public void setCase_name(String case_name) {
		this.case_name = case_name;
	}

	public int compareTo(CurrentLegalCase other) {
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

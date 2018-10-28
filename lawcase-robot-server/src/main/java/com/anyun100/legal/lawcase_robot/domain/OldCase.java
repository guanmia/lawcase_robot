package com.anyun100.legal.lawcase_robot.domain;

import java.time.LocalDateTime;

public class OldCase {
	private Long oldCaseId;
	private String caseNum;
	private String caseDate;
	private String court;
	private String caseContent;
	public OldCase(Long oldCaseId, String caseNum, String caseDate, String court, String caseContent) {
		this.oldCaseId = oldCaseId;
		this.caseNum = caseNum;
		this.caseDate = caseDate;
		this.court = court;
		this.caseContent = caseContent;
	}
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	public Long getOldCaseId() {
		return oldCaseId;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public String getCaseDate() {
		return caseDate;
	}
	public String getCaseContent() {
		return caseContent;
	}
	public void setOldCaseId(Long oldCaseId) {
		this.oldCaseId = oldCaseId;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public void setCaseDate(String caseDate) {
		this.caseDate = caseDate;
	}
	public void setCaseContent(String caseContent) {
		this.caseContent = caseContent;
	}

}

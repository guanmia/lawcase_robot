package com.anyun100.legal.lawcase_robot.domain;

public class AnalysisLaw {

	private Long id;
	private Long analysisId;
	private String lawName;
	private String lawNum;
	private String lawContent;
	public AnalysisLaw(Long id, Long analysisId, String lawName, String lawNum, String lawContent) {
		this.id = id;
		this.analysisId = analysisId;
		this.lawName = lawName;
		this.lawNum = lawNum;
		this.lawContent = lawContent;
	}
	public Long getId() {
		return id;
	}
	public Long getAnalysisId() {
		return analysisId;
	}
	public String getLawName() {
		return lawName;
	}
	public String getLawNum() {
		return lawNum;
	}
	public String getLawContent() {
		return lawContent;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setAnalysisId(Long analysisId) {
		this.analysisId = analysisId;
	}
	public void setLawName(String lawName) {
		this.lawName = lawName;
	}
	public void setLawNum(String lawNum) {
		this.lawNum = lawNum;
	}
	public void setLawContent(String lawContent) {
		this.lawContent = lawContent;
	}
}

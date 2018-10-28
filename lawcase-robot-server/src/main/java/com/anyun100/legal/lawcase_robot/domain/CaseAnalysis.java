package com.anyun100.legal.lawcase_robot.domain;

public class CaseAnalysis {
	
	private String caseId;
	private Long analysisId;
	public CaseAnalysis(String caseId, Long analysisId) {
		this.caseId = caseId;
		this.analysisId = analysisId;
	}
	public String getCaseId() {
		return caseId;
	}
	public Long getAnalysisId() {
		return analysisId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public void setAnalysisId(Long analysisId) {
		this.analysisId = analysisId;
	}

}

package com.anyun100.legal.lawcase_robot.domain;

public class OldCaseAnalysis {
	
	private Long oldCaseId;
	private Long analysisId;
	public OldCaseAnalysis(Long oldCaseId, Long analysisId) {
		this.oldCaseId = oldCaseId;
		this.analysisId = analysisId;
	}
	public Long getOldCaseId() {
		return oldCaseId;
	}
	public Long getAnalysisId() {
		return analysisId;
	}
	public void setOldCaseId(Long oldCaseId) {
		this.oldCaseId = oldCaseId;
	}
	public void setAnalysisId(Long analysisId) {
		this.analysisId = analysisId;
	}

}

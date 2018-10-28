package com.anyun100.legal.lawcase_robot.domain;

public class CategoryOptionAnalysis {
	
	private Long analysisId;
	private Long categoryId;
	private String options;
	private String analysisSummary;
	public CategoryOptionAnalysis(Long analysisId, Long categoryId, String options, String analysisSummary) {
		this.analysisId = analysisId;
		this.categoryId = categoryId;
		this.options = options;
		this.analysisSummary = analysisSummary;
	}
	public String getAnalysisSummary() {
		return analysisSummary;
	}
	public void setAnalysisSummary(String analysisSummary) {
		this.analysisSummary = analysisSummary;
	}
	public Long getAnalysisId() {
		return analysisId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public String getOptions() {
		return options;
	}
	public void setAnalysisId(Long analysisId) {
		this.analysisId = analysisId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	

}

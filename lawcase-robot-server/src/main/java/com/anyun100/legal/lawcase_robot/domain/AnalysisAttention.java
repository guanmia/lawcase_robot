package com.anyun100.legal.lawcase_robot.domain;

public class AnalysisAttention {
	
	private Long id;
	private Long analysisId;
	private String attentionName;
	private String attentionContent;
	public AnalysisAttention(Long id, Long analysisId, String attentionName, String attentionContent) {
		this.id = id;
		this.analysisId = analysisId;
		this.attentionName = attentionName;
		this.attentionContent = attentionContent;
	}
	public Long getId() {
		return id;
	}
	public Long getAnalysisId() {
		return analysisId;
	}
	public String getAttentionName() {
		return attentionName;
	}
	public String getAttentionContent() {
		return attentionContent;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setAnalysisId(Long analysisId) {
		this.analysisId = analysisId;
	}
	public void setAttentionName(String attentionName) {
		this.attentionName = attentionName;
	}
	public void setAttentionContent(String attentionContent) {
		this.attentionContent = attentionContent;
	}

}

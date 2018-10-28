package com.anyun100.legal.lawcase_robot.domain.dto;

import java.io.Serializable;


public class LawAttentionDto implements Serializable {
	
	private String attention_name;
	private String content;
	public LawAttentionDto(String attention_name, String content) {
		this.attention_name = attention_name;
		this.content = content;
	}
	public String getAttention_name() {
		return attention_name;
	}
	public String getContent() {
		return content;
	}
	public void setAttention_name(String attention_name) {
		this.attention_name = attention_name;
	}
	public void setContent(String content) {
		this.content = content;
	}


}

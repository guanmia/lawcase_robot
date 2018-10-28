package com.anyun100.legal.lawcase_robot.domain.dto;

import java.io.Serializable;


public class LawContentDto implements Serializable {
	
	private String law_name;
	private String law_num;
	private String law_content;
	public LawContentDto(String law_name, String law_num, String law_content) {
		this.law_name = law_name;
		this.law_num = law_num;
		this.law_content = law_content;
	}
	public String getLaw_name() {
		return law_name;
	}
	public String getLaw_num() {
		return law_num;
	}
	public String getLaw_content() {
		return law_content;
	}
	public void setLaw_name(String law_name) {
		this.law_name = law_name;
	}
	public void setLaw_num(String law_num) {
		this.law_num = law_num;
	}
	public void setLaw_content(String law_content) {
		this.law_content = law_content;
	}
}

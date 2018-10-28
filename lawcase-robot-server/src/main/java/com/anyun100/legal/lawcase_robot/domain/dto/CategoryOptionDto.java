package com.anyun100.legal.lawcase_robot.domain.dto;

import java.io.Serializable;


public class CategoryOptionDto implements Serializable{
	private String option_name;
	private String value_name;
	private int value;
	
	public CategoryOptionDto(String option_name, String value_name, int value) {
		this.option_name = option_name;
		this.value_name = value_name;
		this.value = value;
	}
	public String getOption_name() {
		return option_name;
	}
	public String getValue_name() {
		return value_name;
	}
	public int getValue() {
		return value;
	}
	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}
	public void setValue_name(String value_name) {
		this.value_name = value_name;
	}
	public void setValue(int value) {
		this.value = value;
	}

}

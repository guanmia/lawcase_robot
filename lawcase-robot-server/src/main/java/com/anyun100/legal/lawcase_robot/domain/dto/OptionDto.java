package com.anyun100.legal.lawcase_robot.domain.dto;

import java.io.Serializable;
import java.util.List;

import com.anyun100.legal.lawcase_robot.domain.Option;

public class OptionDto implements Serializable, Comparable<OptionDto> {
	
	private long option_id;
	private String option_name;
	private List<Option.OptionaNameValue> options;
	private int step;
	public String type;
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public long getOption_id() {
		return option_id;
	}
	public String getOption_name() {
		return option_name;
	}
	public List<Option.OptionaNameValue> getOptions() {
		return options;
	}
	public String getType() {
		return type;
	}
	public void setOption_id(long option_id) {
		this.option_id = option_id;
	}
	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}
	public void setOptions(List<Option.OptionaNameValue> options) {
		this.options = options;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int compareTo(OptionDto other) {
		return (int) (this.option_id - other.option_id);
	}

}

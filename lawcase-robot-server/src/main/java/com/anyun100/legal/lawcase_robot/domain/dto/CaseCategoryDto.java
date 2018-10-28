package com.anyun100.legal.lawcase_robot.domain.dto;

import java.io.Serializable;
import java.util.List;


public class CaseCategoryDto implements Serializable, Comparable<CaseCategoryDto> {
	
	private long category_id;
	private String category_name;
	private String parent_name;
	
	public CaseCategoryDto(long category_id, String category_name, String parent_name) {
		this.category_id = category_id;
		this.category_name = category_name;
		this.parent_name = parent_name;
	}

	public long getCategory_id() {
		return category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public int compareTo(CaseCategoryDto other) {
		return (int) (this.category_id - other.category_id);
	}

}

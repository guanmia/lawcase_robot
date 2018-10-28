package com.anyun100.legal.lawcase_robot.domain.dto;

import java.io.Serializable;
import java.util.List;


public class CaseCategoryOptionDto implements Serializable, Comparable<CaseCategoryOptionDto> {
	
	private long category_id;
	private String category_name;
	private String parent_name;
	private List<CategoryOptionDto> options;
	 
	public CaseCategoryOptionDto(long category_id, String category_name, String parent_name, List<CategoryOptionDto> options) {
		this.category_id = category_id;
		this.category_name = category_name;
		this.parent_name = parent_name;
		this.options = options;
	}

	public List<CategoryOptionDto> getOptions() {
		return options;
	}

	public void setCategoryOptionDtos(List<CategoryOptionDto> options) {
		this.options = options;
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

	public int compareTo(CaseCategoryOptionDto other) {
		return (int) (this.category_id - other.category_id);
	}

}

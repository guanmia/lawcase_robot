package com.anyun100.legal.lawcase_robot.domain.dto;

import java.io.Serializable;


public class CategoryDto implements Serializable, Comparable<CategoryDto> {
	
	private long category_id;
	private String category_name;
	private long parentid;
	public long getCategory_id() {
		return category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	public long getParentid() {
		return parentid;
	}
	public void setParentid(long parentid) {
		this.parentid = parentid;
	}
	public int compareTo(CategoryDto other) {
		return (int) (this.category_id - other.category_id);
	}

}

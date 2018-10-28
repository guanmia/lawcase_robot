package com.anyun100.legal.lawcase_robot.domain.dto;

import java.io.Serializable;
import java.util.List;


public class ParentChildCategoryDto implements Serializable, Comparable<ParentChildCategoryDto>{
	
	private long category_id;
	private String category_name;
	private long parentid;
	private List<CategoryDto> children;
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
	public List<CategoryDto> getChildren() {
		return children;
	}
	public void setChildren(List<CategoryDto> children) {
		this.children = children;
	}
	
	public int compareTo(ParentChildCategoryDto other) {
		return (int) (this.category_id - other.category_id);
	}

}

package com.anyun100.legal.lawcase_robot.domain.dto;

import java.io.Serializable;


public class InfluenceDto implements Serializable {
	
	private String influence_name;
	private String influence_id;
	public InfluenceDto(String influence_name, String influence_id) {
		this.influence_name = influence_name;
		this.influence_id = influence_id;
	}
	public String getInfluence_name() {
		return influence_name;
	}
	public String getInfluence_id() {
		return influence_id;
	}
	public void setInfluence_name(String influence_name) {
		this.influence_name = influence_name;
	}
	public void setInfluence_id(String influence_id) {
		this.influence_id = influence_id;
	}
	

}

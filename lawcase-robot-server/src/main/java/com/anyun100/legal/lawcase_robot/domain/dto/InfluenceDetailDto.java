package com.anyun100.legal.lawcase_robot.domain.dto;

import java.io.Serializable;


public class InfluenceDetailDto implements Serializable {
	
	private String pt_influence;
	private boolean pt_increase;
	private String pt_in_min;
	private String pt_in_max;
	private String pt_noin_min;
	private String pt_noin_max;
	private String reprieve_influence;
	private boolean reprieve_increase;
	private String reprieve_in;
	private String reprieve_noin;
	public InfluenceDetailDto(String pt_influence, boolean pt_increase, String pt_in_min, String pt_in_max,
			String pt_noin_min, String pt_noin_max, String reprieve_influence, boolean reprieve_increase,
			String reprieve_in, String reprieve_noin) {
		this.pt_influence = pt_influence;
		this.pt_increase = pt_increase;
		this.pt_in_min = pt_in_min;
		this.pt_in_max = pt_in_max;
		this.pt_noin_min = pt_noin_min;
		this.pt_noin_max = pt_noin_max;
		this.reprieve_influence = reprieve_influence;
		this.reprieve_increase = reprieve_increase;
		this.reprieve_in = reprieve_in;
		this.reprieve_noin = reprieve_noin;
	}
	public String getPt_influence() {
		return pt_influence;
	}
	public boolean isPt_increase() {
		return pt_increase;
	}
	public String getPt_in_min() {
		return pt_in_min;
	}
	public String getPt_in_max() {
		return pt_in_max;
	}
	public String getPt_noin_min() {
		return pt_noin_min;
	}
	public String getPt_noin_max() {
		return pt_noin_max;
	}
	public String getReprieve_influence() {
		return reprieve_influence;
	}
	public boolean isReprieve_increase() {
		return reprieve_increase;
	}
	public String getReprieve_in() {
		return reprieve_in;
	}
	public String getReprieve_noin() {
		return reprieve_noin;
	}
	public void setPt_influence(String pt_influence) {
		this.pt_influence = pt_influence;
	}
	public void setPt_increase(boolean pt_increase) {
		this.pt_increase = pt_increase;
	}
	public void setPt_in_min(String pt_in_min) {
		this.pt_in_min = pt_in_min;
	}
	public void setPt_in_max(String pt_in_max) {
		this.pt_in_max = pt_in_max;
	}
	public void setPt_noin_min(String pt_noin_min) {
		this.pt_noin_min = pt_noin_min;
	}
	public void setPt_noin_max(String pt_noin_max) {
		this.pt_noin_max = pt_noin_max;
	}
	public void setReprieve_influence(String reprieve_influence) {
		this.reprieve_influence = reprieve_influence;
	}
	public void setReprieve_increase(boolean reprieve_increase) {
		this.reprieve_increase = reprieve_increase;
	}
	public void setReprieve_in(String reprieve_in) {
		this.reprieve_in = reprieve_in;
	}
	public void setReprieve_noin(String reprieve_noin) {
		this.reprieve_noin = reprieve_noin;
	}
	
	

}

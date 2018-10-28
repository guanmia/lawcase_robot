package com.anyun100.legal.lawcase_robot.service;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("page")
public class PageProperties {

    private long defaultPageSize = 20;

	public long getDefaultPageSize() {
		return defaultPageSize;
	}

	public void setDefaultPageSize(long defaultPageSize) {
		this.defaultPageSize = defaultPageSize;
	}

}

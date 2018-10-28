package com.anyun100.legal.lawcase_robot.service;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";
    
    private String caseJsonFileName = "user_current_cases.json";

    public String getCaseJsonFileName() {
		return caseJsonFileName;
	}

	public void setCaseJsonFileName(String caseJsonFileName) {
		this.caseJsonFileName = caseJsonFileName;
	}

	public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}

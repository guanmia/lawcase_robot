package com.anyun100.legal.lawcase_robot.service;


import com.anyun100.legal.lawcase_robot.domain.dto.BaseCaseDto;

public interface CaseAddService {
	
	BaseCaseDto addCase(String openId, String case_id, String case_num, String case_date, String case_content, String fileName);

}

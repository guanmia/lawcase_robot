package com.anyun100.legal.lawcase_robot.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.anyun100.legal.lawcase_robot.domain.dto.BaseCaseDto;
import com.anyun100.legal.lawcase_robot.service.CaseAddService;
import com.anyun100.legal.lawcase_robot.service.StorageService;

@RestController
@RequestMapping("/lawrobot/api/case")
public class CaseAddController {

	@Autowired
	private StorageService storageService;
	@Autowired
	private CaseAddService caseAddService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public BaseCaseDto addCase(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "case_num", required = true) String case_num,
			@RequestParam(value = "court_datetime", required = true) String court_datetime,
			@RequestParam(value = "case_content", required = false) String case_content,
			@RequestParam(value= "case_file", required = false) MultipartFile file) {
		
		String case_id = UUID.randomUUID().toString().replaceAll("-", "");
		if (file != null && !file.isEmpty()) {
			storageService.store(file, case_id);
			return caseAddService.addCase(openid, case_id, case_num, court_datetime, case_content, case_id);
		}
		return caseAddService.addCase(openid, case_id, case_num, court_datetime, case_content, "");
	}

}

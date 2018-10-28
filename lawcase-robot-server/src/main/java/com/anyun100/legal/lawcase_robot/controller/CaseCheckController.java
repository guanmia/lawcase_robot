package com.anyun100.legal.lawcase_robot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.anyun100.legal.lawcase_robot.domain.dto.BaseCaseDto;
import com.anyun100.legal.lawcase_robot.service.CaseCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/lawrobot/api/case")
public class CaseCheckController {
	
	@Autowired
	private CaseCheckService caseCheckService;
	
	private static final Logger logger = LoggerFactory.getLogger(CaseCheckController.class);

	@RequestMapping(value = "/check", method=RequestMethod.POST)
    @ResponseBody
    public BaseCaseDto isCaseExisted(@RequestParam(value="openid", required=true) String openid) {
		
		logger.info("Check case if existed for opend id with {} ", openid);
    	
    	return caseCheckService.isCaseExisted(openid);
    }
	
	@RequestMapping(value = "/alert", method=RequestMethod.POST)
    @ResponseBody
    public BaseCaseDto getExistingCases(@RequestParam(value="openid", required=true) String openid) {
    	
    	return caseCheckService.getExistingCases(openid);
    }
	
	@RequestMapping(value = "/category", method=RequestMethod.POST)
    @ResponseBody
    public JSONObject getCaseCategory(@RequestParam(value="case_id", required=true) String case_id) {
    	
    	return caseCheckService.getCaseCategoryJSONObject(case_id);
    }

	@RequestMapping(value = "/list", method=RequestMethod.POST)
    @ResponseBody
    public BaseCaseDto listCasesExisted(@RequestParam(value="openid", required=true) String openid) {
		logger.info("List case for opend id with {} ", openid);
    	return caseCheckService.listCasesExisted(openid);
    }
	
	@RequestMapping(value = "/content", method=RequestMethod.POST)
    @ResponseBody
    public BaseCaseDto getCaseContent(@RequestParam(value="case_id", required=true) String case_id) {
    	
    	return caseCheckService.getCaseContent(case_id);
    }
}

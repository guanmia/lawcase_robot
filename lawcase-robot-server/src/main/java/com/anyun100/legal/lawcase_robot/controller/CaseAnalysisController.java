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


@RestController
@RequestMapping("/lawrobot/api/case/analysis")
public class CaseAnalysisController {
	
	@Autowired
	private CaseCheckService caseCheckService;
	
	@RequestMapping(value = "/similarcase", method = RequestMethod.POST)
    @ResponseBody
    public BaseCaseDto analysisSimilarcase(@RequestParam(value="case_id", required=true) String case_id, 
											@RequestParam(value="page", required=true) int page,
											@RequestParam(value="page_count", required=true) int page_count,
											@RequestParam(value="page_count", required=false) int sort) {
												
											
    	return caseCheckService.analysisSimilarcase(case_id, page, page_count);
    }
	
	@RequestMapping(value = "/law", method=RequestMethod.POST)
    @ResponseBody
    public BaseCaseDto getAnalysisLaw(@RequestParam(value="case_id", required=true) String case_id) {
												
											
    	return caseCheckService.getAnalysisLaw(case_id);
    }
	
	@RequestMapping(value = "attention", method=RequestMethod.POST)
    @ResponseBody
    public BaseCaseDto getAnalysisAttention(@RequestParam(value="case_id", required=true) String case_id) {
												
											
    	return caseCheckService.getAnalysisAttention(case_id);
    }
	
	@RequestMapping(value = "influence", method=RequestMethod.POST)
    @ResponseBody
    public BaseCaseDto getAnalysisInfluence(@RequestParam(value="case_id", required=true) String case_id) {
												
											
    	return caseCheckService.getAnalysisInfluence(case_id);
    }
	
	@RequestMapping(value = "influencedetail", method=RequestMethod.POST)
    @ResponseBody
    public JSONObject getAnalysisInfluencedetail(@RequestParam(value="influence_id", required=true) Long influence_id) {										
		return caseCheckService.getAnalysisInfluencedetail(influence_id);
    }
	
}

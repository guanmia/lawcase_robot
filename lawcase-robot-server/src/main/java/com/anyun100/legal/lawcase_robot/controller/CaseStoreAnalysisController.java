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
import com.anyun100.legal.lawcase_robot.service.CaseStoreCheckService;
import com.google.common.collect.ImmutableMap;


@RestController
@RequestMapping("/lawrobot/api/casestore")
public class CaseStoreAnalysisController {
	
	@Autowired
	private CaseCheckService caseCheckService;
	
	@Autowired
	private CaseStoreCheckService caseStoreCheckService;
	
	@RequestMapping(value = "/analysis/similarcase", method=RequestMethod.POST)
    @ResponseBody
    public BaseCaseDto analysisSimilarcase(@RequestParam(value="analysis_id", required=true) Long analysis_id, 
											@RequestParam(value="page", required=true) int page,
											@RequestParam(value="page_count", required=true) int page_count,
											@RequestParam(value="page_count", required=false) int sort) {
												
											
    	return caseStoreCheckService.analysisSimilarcase(analysis_id, page, page_count);
    }
	
	@RequestMapping(value = "/analysis/law", method=RequestMethod.POST)
    @ResponseBody
    public BaseCaseDto getAnalysisLaw(@RequestParam(value="analysis_id", required=true) Long analysis_id) {
												
											
    	return caseStoreCheckService.getAnalysisLaw(analysis_id);
    }
	
	@RequestMapping(value = "/analysis/attention", method=RequestMethod.POST)
    @ResponseBody
    public BaseCaseDto getAnalysisAttention(@RequestParam(value="analysis_id", required=true) Long analysis_id) {
												
											
    	return caseStoreCheckService.getAnalysisAttention(analysis_id);
    }
	
	@RequestMapping(value = "/analysis/influence", method=RequestMethod.POST)
    @ResponseBody
    public BaseCaseDto getAnalysisInfluence(@RequestParam(value="analysis_id", required=true) Long analysis_id) {
												
											
    	return caseStoreCheckService.getAnalysisInfluence(analysis_id);
    }
	
	@RequestMapping(value = "/analysis/influencedetail", method=RequestMethod.POST)
    @ResponseBody
    public JSONObject getAnalysisInfluencedetail(@RequestParam(value="influence_id", required=true) Long influence_id) {
												
											
    	return caseCheckService.getAnalysisInfluencedetail(influence_id);
    }
	
	@RequestMapping(value = "search", method=RequestMethod.POST)
    @ResponseBody
    public BaseCaseDto getanalysisId(@RequestParam(value="category_id", required=true) Long category_id,
    								@RequestParam(value="searchkeys", required=true) String searchkeys) {
																
		return caseStoreCheckService.getAnalysisId(category_id, searchkeys);
    }
	
	@RequestMapping(value = "content", method=RequestMethod.POST)
    @ResponseBody
    public JSONObject getContents(@RequestParam(value="case_id", required=true) String case_id) {
																
		try {
			JSONObject json = new JSONObject();
			json.put("error_code", 0);
			json.put("data", ImmutableMap.of("case_id", case_id, "case_num", "案号","case_content", "案件内容",  "case_datetime", "2017-9-30 10:00" ));
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
    }
	
	
}

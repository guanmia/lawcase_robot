package com.anyun100.legal.lawcase_robot.service;

import com.alibaba.fastjson.JSONObject;

import com.anyun100.legal.lawcase_robot.domain.dto.BaseCaseDto;

public interface CaseCheckService {
	
	BaseCaseDto getExistingCases(String openId);
	BaseCaseDto isCaseExisted(String openId);
	BaseCaseDto listCasesExisted(String openId);
	BaseCaseDto getCaseCategory(String case_id);
	JSONObject getCaseCategoryJSONObject(String case_id);
	BaseCaseDto analysisSimilarcase(String case_id, int page, int pageCount);
	BaseCaseDto getAnalysisLaw(String case_id);
	BaseCaseDto getCaseContent(String case_id);
	BaseCaseDto getAnalysisAttention(String case_id);
	BaseCaseDto getAnalysisInfluence(String case_id);
	JSONObject getAnalysisInfluencedetail(Long influent_id);
}

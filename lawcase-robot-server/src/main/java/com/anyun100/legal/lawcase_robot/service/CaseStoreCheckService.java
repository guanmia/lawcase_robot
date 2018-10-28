package com.anyun100.legal.lawcase_robot.service;


import com.anyun100.legal.lawcase_robot.domain.dto.BaseCaseDto;

public interface CaseStoreCheckService {
	BaseCaseDto analysisSimilarcase(Long analysis_id, int page, int pageCount);
	BaseCaseDto getAnalysisLaw(Long analysis_id);
	BaseCaseDto getAnalysisAttention(Long analysis_id);
	BaseCaseDto getAnalysisInfluence(Long analysis_id);
	BaseCaseDto getAnalysisId(Long category_id, String options);
}

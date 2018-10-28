package com.anyun100.legal.lawcase_robot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anyun100.legal.lawcase_robot.domain.CurrentLegalCase;
import com.anyun100.legal.lawcase_robot.domain.UserCurrentLegalCase;
import com.anyun100.legal.lawcase_robot.domain.dto.BaseCaseDto;
import com.anyun100.legal.lawcase_robot.mapper.CaseAnalysisMapper;
import com.anyun100.legal.lawcase_robot.mapper.CategoryOptionAnalysisMapper;
import com.anyun100.legal.lawcase_robot.util.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@Service
public class CaseAddServiceImpl implements CaseAddService {

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private LegalCaseStore legalCaseStore;
	
	@Autowired
	private StorageProperties storageProperties;
	
	@Autowired
	private CaseAnalysisMapper caseAnalysisMapper;
	
	@Autowired
	private CategoryOptionAnalysisMapper categoryOptionAnalysisMapper;

	public BaseCaseDto addCase(String openId, String caseId, String case_num, String case_date,
			String case_content, String fileName) {
		if (DateTimeUtil.parseLocalDateTime(case_date) != null) {
			List<CurrentLegalCase> currentLegalCases = legalCaseStore.getCurrentLegalCaseStore().get(openId);
			CurrentLegalCase currentLegalCase = new CurrentLegalCase();
			currentLegalCase.setCaseId(caseId);
			currentLegalCase.setCase_num(case_num);
			currentLegalCase.setCourt_date(case_date);
			currentLegalCase.setCase_content(case_content);
			currentLegalCase.setContent_file_name(fileName);
			if (currentLegalCases != null) {
				currentLegalCases.add(currentLegalCase);
			} else {
				currentLegalCases = new ArrayList<>();
				currentLegalCases.add(currentLegalCase);
				legalCaseStore.getCurrentLegalCaseStore().put(openId, currentLegalCases);
			}
			storeCase(openId, currentLegalCase);
			addFakeAnalysis(caseId);
			return new BaseCaseDto(0, ImmutableMap.of("case_id", caseId));
		} else return new BaseCaseDto(1, ImmutableMap.of());
	}

	private void storeCase(String openId, CurrentLegalCase currentLegalCase) {
		ObjectMapper mapper = new ObjectMapper();
		UserCurrentLegalCase obj = new UserCurrentLegalCase();
		obj.setOpenid(openId);
		obj.setCurrentLegalCases(ImmutableList.of(currentLegalCase));
		try {
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		    mapper.setDateFormat(new ISO8601DateFormat());
			String json = mapper.writeValueAsString(obj);
			storageService.append(json + "\n", storageProperties.getCaseJsonFileName());
		} catch(JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	private void addFakeAnalysis(String caseId) {
		if (caseAnalysisMapper.getCaseAnalysis(caseId) != null) {
			return;
		}
		Random randomno = new Random();
		List<Long> analysisIds = categoryOptionAnalysisMapper.getAllAnalysisIds();
		caseAnalysisMapper.insert(caseId, (long) randomno.nextInt(analysisIds.size()) + 1L);
	}

}

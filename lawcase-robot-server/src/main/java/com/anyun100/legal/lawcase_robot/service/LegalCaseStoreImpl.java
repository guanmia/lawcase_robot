package com.anyun100.legal.lawcase_robot.service;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anyun100.legal.lawcase_robot.domain.CurrentLegalCase;
import com.anyun100.legal.lawcase_robot.domain.UserCurrentLegalCase;
import com.anyun100.legal.lawcase_robot.mapper.CaseAnalysisMapper;
import com.anyun100.legal.lawcase_robot.mapper.CategoryOptionAnalysisMapper;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;

@Service
public class LegalCaseStoreImpl implements LegalCaseStore {

	@Autowired
	private StorageService storageService;

	@Autowired
	private StorageProperties storageProperties;
	
	@Autowired
	private CaseAnalysisMapper caseAnalysisMapper;
	
	@Autowired
	private CategoryOptionAnalysisMapper categoryOptionAnalysisMapper;

	private static final Map<String, List<CurrentLegalCase>> currentLegalCaseStore = new HashMap<>();

	public Map<String, List<CurrentLegalCase>> getCurrentLegalCaseStore() {
		return currentLegalCaseStore;
	}

	public void initStore() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		try {
			File storeFile = storageService.load(storageProperties.getCaseJsonFileName()).toFile();
			if (storeFile.exists() && storeFile.length() > 0) {
				JsonFactory factory = new JsonFactory();
				JsonParser parser = factory.createJsonParser(storeFile);
				MappingIterator<UserCurrentLegalCase> userCurrentLegalCases = mapper.readValues(parser,
						UserCurrentLegalCase.class);
				while (userCurrentLegalCases.hasNext()) {
					UserCurrentLegalCase userCurrentLegalCase = userCurrentLegalCases.next();
					List<CurrentLegalCase> currentLegalCases = currentLegalCaseStore
							.get(userCurrentLegalCase.getOpenid());
					if (currentLegalCases != null) {
						currentLegalCases.addAll(userCurrentLegalCase.getCurrentLegalCases());
					} else {
						currentLegalCaseStore.put(userCurrentLegalCase.getOpenid(),
								userCurrentLegalCase.getCurrentLegalCases());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		setupFakeCaseAnalysis();
	}
	
	private void setupFakeCaseAnalysis() {
		caseAnalysisMapper.deleteAll();
		List<Long> analysisIds = categoryOptionAnalysisMapper.getAllAnalysisIds();
		Random randomno = new Random();
		currentLegalCaseStore.values().stream().flatMap(c -> c.stream()).collect(Collectors.toList()).stream()
																	    .filter(c -> caseAnalysisMapper.getCaseAnalysis(c.getCaseId()) == null)
																	    .forEach(c -> caseAnalysisMapper.insert(c.getCaseId(), (long) randomno.nextInt(analysisIds.size()) + 1L));
	}
}

package com.anyun100.legal.lawcase_robot.service;

import java.util.List;
import java.util.Map;
import com.anyun100.legal.lawcase_robot.domain.CurrentLegalCase;

public interface LegalCaseStore {
	Map<String, List<CurrentLegalCase>> getCurrentLegalCaseStore();
	void initStore();

}

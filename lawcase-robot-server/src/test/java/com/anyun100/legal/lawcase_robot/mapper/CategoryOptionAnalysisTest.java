package com.anyun100.legal.lawcase_robot.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryOptionAnalysisTest {

	@Autowired
	private CategoryOptionAnalysisMapper categoryOptionAnalysisMapper;

	@Test
	public void testQuery() throws Exception {
		Long analysisId = categoryOptionAnalysisMapper.getAnalysisId(1L, "options");
		System.out.println(analysisId);
	}

}
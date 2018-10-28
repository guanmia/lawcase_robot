package com.anyun100.legal.lawcase_robot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.anyun100.legal.lawcase_robot.domain.CaseAnalysis;
import com.anyun100.legal.lawcase_robot.domain.OldCaseAnalysis;

public interface OldCaseAnalysisMapper {

	@Select("SELECT * FROM analysis_oldcase")
	@Results({
		@Result(property = "analysisId",  column = "analysis_id"),
		@Result(property = "oldCaseId", column = "oldcase_id")
	})
	List<OldCaseAnalysis> getAll();
	
	@Select("SELECT * FROM analysis_oldcase WHERE analysis_id = #{analysisId}")
	@Results({
		@Result(property = "analysisId",  column = "analysis_id"),
		@Result(property = "oldCaseId", column = "oldcase_id")
	})
	List<OldCaseAnalysis> getOldCaseAnalysis(Long analysisId);
}

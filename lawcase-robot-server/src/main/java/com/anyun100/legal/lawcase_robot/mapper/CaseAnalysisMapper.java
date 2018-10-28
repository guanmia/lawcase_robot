package com.anyun100.legal.lawcase_robot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.anyun100.legal.lawcase_robot.domain.CaseAnalysis;

public interface CaseAnalysisMapper {

	@Select("SELECT * FROM case_analysis")
	@Results({
		@Result(property = "analysisId",  column = "analysis_id"),
		@Result(property = "caseId", column = "case_id")
	})
	List<CaseAnalysis> getAll();
	
	@Select("SELECT * FROM case_analysis WHERE case_id = #{caseId}")
	@Results({
		@Result(property = "analysisId",  column = "analysis_id"),
		@Result(property = "caseId", column = "case_id")
	})
	CaseAnalysis getCaseAnalysis(String caseId);
	
	@Insert("INSERT INTO case_analysis(case_id,analysis_id) VALUES(#{caseId}, #{analysisId})")
	void insert( @Param("caseId")String caseId, @Param("analysisId")Long analysisId);
	
	@Delete("DELETE FROM case_analysis")
	void deleteAll();
}

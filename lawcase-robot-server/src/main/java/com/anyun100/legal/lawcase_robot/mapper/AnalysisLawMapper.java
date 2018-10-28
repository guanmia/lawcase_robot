package com.anyun100.legal.lawcase_robot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.anyun100.legal.lawcase_robot.domain.AnalysisLaw;

public interface AnalysisLawMapper {
	
	@Select("SELECT * FROM analysis_law")
	@Results({
		@Result(property = "id",  column = "id"),
		@Result(property = "analysisId",  column = "analysis_id"),
		@Result(property = "lawName", column = "law_name"),
		@Result(property = "lawNum", column = "law_num"),
		@Result(property = "lawContent", column = "law_content")
		
	})
	List<AnalysisLaw> getAll();
	
	@Select("SELECT * FROM analysis_law WHERE analysis_id = #{analysisId}")
	@Results({
		@Result(property = "id",  column = "id"),
		@Result(property = "analysisId",  column = "analysis_id"),
		@Result(property = "lawName", column = "law_name"),
		@Result(property = "lawNum", column = "law_num"),
		@Result(property = "lawContent", column = "law_content")
	})
	List<AnalysisLaw> getAnalysisLaws(Long analysisId);

}

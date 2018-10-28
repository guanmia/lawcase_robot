package com.anyun100.legal.lawcase_robot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.anyun100.legal.lawcase_robot.domain.CategoryOptionAnalysis;
import com.anyun100.legal.lawcase_robot.domain.User;

public interface CategoryOptionAnalysisMapper {
	
	@Select("SELECT * FROM category_option_analysis")
	@Results({
		@Result(property = "analysisId",  column = "analysis_id"),
		@Result(property = "categoryId", column = "category_id"),
		@Result(property = "options", column = "options")
	})
	List<CategoryOptionAnalysis> getAll();
	
	@Select("select analysis_id from category_option_analysis WHERE category_id = #{categoryId} and options = #{options}")
	@Results({
		@Result(column = "analysis_id", javaType=java.lang.Long.class)
	})
	Long getAnalysisId(@Param("categoryId")Long categoryId, @Param("options")String options);
	
	@Select("select * from category_option_analysis WHERE category_id = #{categoryId}")
	@Results({
		@Result(property = "analysisId",  column = "analysis_id"),
		@Result(property = "categoryId", column = "category_id"),
		@Result(property = "options", column = "options"),
		@Result(property = "analysisSummary", column = "analysis_summary")
	})
	List<CategoryOptionAnalysis> getAllBy(@Param("categoryId")Long categoryId);
	
	@Select("select category_id from category_option_analysis WHERE analysis_id = #{analysisId}")
	@Results({
		@Result(column = "category_id", javaType=java.lang.Long.class)
	})
	Long getCategoryId(@Param("analysisId")Long analysisId);
	
	@Select("select analysis_id from category_option_analysis")
	@Results({
		@Result(column = "analysis_id", javaType=java.lang.Long.class)
	})
	List<Long> getAllAnalysisIds();
	
	@Select("select options from category_option_analysis WHERE analysis_id = #{analysisId}")
	@Results({
		@Result(column = "options", javaType=java.lang.String.class)
	})
	String getOptions(@Param("analysisId")Long analysisId);

}
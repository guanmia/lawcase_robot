package com.anyun100.legal.lawcase_robot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.anyun100.legal.lawcase_robot.domain.AnalysisAttention;

public interface AnalysisAttentionMapper {
	
	@Select("SELECT * FROM analysis_attention")
	@Results({
		@Result(property = "id",  column = "id"),
		@Result(property = "analysisId",  column = "analysis_id"),
		@Result(property = "attentionName", column = "attention_name"),
		@Result(property = "attentionContent", column = "attention_content")
		
	})
	List<AnalysisAttention> getAll();
	
	@Select("SELECT * FROM analysis_attention WHERE analysis_id = #{analysisId}")
	@Results({
		@Result(property = "id",  column = "id"),
		@Result(property = "analysisId",  column = "analysis_id"),
		@Result(property = "attentionName", column = "attention_name"),
		@Result(property = "attentionContent", column = "attention_content")
	})
	List<AnalysisAttention> getAnalysisAttentions(Long analysisId);

}

package com.anyun100.legal.lawcase_robot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import com.anyun100.legal.lawcase_robot.domain.OldCase;

public interface OldCaseMapper {

	@Select("SELECT *  FROM oldcase")
	@Results({
		@Result(property = "caseDate",  column = "court_datetime"),
		@Result(property = "oldCaseId", column = "oldcase_id"),
		@Result(property = "caseNum",  column = "case_num"),
		@Result(property = "court",  column = "court"),
		@Result(property = "caseContent", column = "case_content")
	})
	List<OldCase> getAll();
	
	@Select("<script>"+
	         "SELECT oldcase_id, case_num,  date_format(court_datetime, '%Y-%m-%d %H') court_datetime, court, case_content  FROM oldcase WHERE oldcase_id IN " +
	           "<foreach collection='oldCaseIds' item='oldCaseId' " +
	             " open='(' separator=',' close=')'>" +
	             " #{oldCaseId}" +
	           "</foreach>" +
	             "order by court_datetime" +
	         "</script>") 
	@Results({
		@Result(property = "oldCaseId", column = "oldcase_id"),
		@Result(property = "caseNum",  column = "case_num"),
		@Result(property = "caseDate",  column = "court_datetime"),
		@Result(property = "court",  column = "court"),
		@Result(property = "caseContent", column = "case_content")
	})
	List<OldCase> getOldCases(@Param("oldCaseIds")List<Long> oldCaseIds);

}

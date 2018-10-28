package com.anyun100.legal.lawcase_robot.service;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anyun100.legal.lawcase_robot.domain.AnalysisAttention;
import com.anyun100.legal.lawcase_robot.domain.CategoryOptionAnalysis;
import com.anyun100.legal.lawcase_robot.domain.OldCase;
import com.anyun100.legal.lawcase_robot.domain.OldCaseAnalysis;
import com.anyun100.legal.lawcase_robot.domain.dto.BaseCaseDto;
import com.anyun100.legal.lawcase_robot.domain.dto.InfluenceDto;
import com.anyun100.legal.lawcase_robot.domain.dto.LawAttentionDto;
import com.anyun100.legal.lawcase_robot.domain.dto.LawContentDto;
import com.anyun100.legal.lawcase_robot.domain.dto.OldCaseDto;
import com.anyun100.legal.lawcase_robot.domain.dto.OptionSearchDto;
import com.anyun100.legal.lawcase_robot.mapper.AnalysisAttentionMapper;
import com.anyun100.legal.lawcase_robot.mapper.AnalysisLawMapper;
import com.anyun100.legal.lawcase_robot.mapper.CategoryOptionAnalysisMapper;
import com.anyun100.legal.lawcase_robot.mapper.OldCaseAnalysisMapper;
import com.anyun100.legal.lawcase_robot.mapper.OldCaseMapper;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

@Service
public class CaseStoreCheckServiceImpl implements CaseStoreCheckService{
	
	@Autowired
	private CategoryOptionAnalysisMapper categoryOptionAnalysisMapper;
	
	@Autowired
	private AnalysisLawMapper analysisLawMapper;
	
	@Autowired
	private AnalysisAttentionMapper analysisAttentionMapper;
	
	@Autowired
	private OldCaseMapper oldCaseMapper;
	
	private OldCaseAnalysisMapper oldCaseAnalysisMapper;
	
	public BaseCaseDto analysisSimilarcase(Long analysisId, int page, int pageCount) {
		List<OldCaseAnalysis> oldCaseAnalysises = oldCaseAnalysisMapper.getOldCaseAnalysis(analysisId);
		if (!oldCaseAnalysises.isEmpty()) {
			List<Long> oldCaseId = oldCaseAnalysises.stream().map(OldCaseAnalysis :: getOldCaseId).collect(Collectors.toList());
			List<OldCase> oldCases = oldCaseMapper.getOldCases(oldCaseId);
			List<OldCaseDto> oldCaseDtos = new ArrayList<>();
			oldCases.stream().forEach(o -> oldCaseDtos.add(new OldCaseDto(o.getOldCaseId(), o.getCaseNum(), o.getCaseDate(), o.getCourt())));
			if (!oldCases.isEmpty()) {
				return new BaseCaseDto(0, ImmutableMap.<String, Serializable>builder()
									.put("analysis_id", analysisId)
									.put("count", oldCaseDtos.size())
									.put("page", page)
									.put("page_count", pageCount)
									.put("results", oldCaseDtos.toArray()).build());
			}
		}
		//TODO: fake return to be removed
		return new BaseCaseDto(0, ImmutableMap.<String, Serializable>builder()
				.put("analysis_id", analysisId)
				.put("count", getOldCaseDtos().size())
				.put("page", page)
				.put("page_count", pageCount)
				.put("results", getOldCaseDtos().toArray()).build());
	}
	
	public BaseCaseDto getAnalysisLaw(Long analysisId) {
		if (analysisLawMapper.getAnalysisLaws(analysisId) != null
				&& !analysisLawMapper.getAnalysisLaws(analysisId).isEmpty()) {
			List<LawContentDto> lawContentDtos = new ArrayList<>();
			analysisLawMapper.getAnalysisLaws(analysisId).stream().forEach(a -> lawContentDtos.add(new LawContentDto(a.getLawName(), a.getLawNum(), a.getLawContent())));
			return new BaseCaseDto(0, ImmutableMap.<String, Serializable>builder()
					.put("analysis_id", analysisId)
					.put("count", lawContentDtos.size())
					.put("results", lawContentDtos.toArray()).build());
		}
		//TODO: fake return to be removed
		return new BaseCaseDto(0, ImmutableMap.<String, Serializable>builder()
				.put("analysis_id", analysisId)
				.put("count", getLawContentDtos().size())
				.put("results", getLawContentDtos().toArray()).build());
	}
	
	public BaseCaseDto getAnalysisAttention(Long analysisId) {
		List<AnalysisAttention> analysisAttentions = analysisAttentionMapper.getAnalysisAttentions(analysisId);			
		if (!analysisAttentions.isEmpty()) {
			List<LawAttentionDto> lawAttentionDtos = new ArrayList<>();
			analysisAttentions.stream().forEach(a -> lawAttentionDtos.add(new LawAttentionDto(a.getAttentionName(), a.getAttentionContent())));
			return new BaseCaseDto(0, ImmutableMap.<String, Serializable>builder()
					.put("analysis_id", analysisId)
					.put("count", lawAttentionDtos.size())
					.put("results", lawAttentionDtos.toArray()).build());
		}
		//TODO: fake return to be removed
		return new BaseCaseDto(0, ImmutableMap.<String, Serializable>builder()
				.put("analysis_id", analysisId)
				.put("count", getLawAttentionDto().size())
				.put("results", getLawAttentionDto().toArray()).build());
	}
	public BaseCaseDto getAnalysisInfluence(Long analysis_id) {
		return new BaseCaseDto(0, ImmutableMap.<String, Serializable>builder()
				.put("analysis_id", analysis_id)
				.put("influence_intro", "系统分析了16个要素.........")
				.put("results", getInfluenceDtos().toArray()).build());
	}
	
	public BaseCaseDto getAnalysisId(Long categoryId, String options) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			OptionSearchDto[] optionSearchDtos = mapper.readValue(options, OptionSearchDto[].class);
			Long analysisId = getAnalysisIdByMatachSearchOption(categoryId, optionSearchDtos);
			if (analysisId != null) {
				return new BaseCaseDto(0, ImmutableMap.of("analysis_id", analysisId));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new BaseCaseDto(1, ImmutableMap.of());
	}
	
	private Long getAnalysisIdByMatachSearchOption(Long categoryId, OptionSearchDto... OptionSearchDtos) throws Exception {
		List<CategoryOptionAnalysis> categoryOptions = categoryOptionAnalysisMapper.getAllBy(categoryId);
		if (categoryOptions != null) {	
			for (CategoryOptionAnalysis categoryOptionAnalysis : categoryOptions) {	
				ObjectMapper mapper = new ObjectMapper();
				OptionSearchDto[] _optionSearchDtos = mapper.readValue(categoryOptionAnalysis.getOptions(), OptionSearchDto[].class);
				if (_optionSearchDtos != null 
					&& OptionSearchDtos.length == OptionSearchDtos.length
					&& Arrays.stream(OptionSearchDtos).filter(o -> {return Arrays.stream(_optionSearchDtos).filter(_o -> !_o.equals(o)).count() <= 0;}).collect(Collectors.toList()).isEmpty()) {
					return categoryOptionAnalysis.getAnalysisId();
				}
			}
		}
		return null;
	}
	
	private List<LawAttentionDto> getLawAttentionDto() {
		List<LawAttentionDto> list = new ArrayList<>();
		list.add(new LawAttentionDto("妨害公务罪的构成", "妨害公务罪是指......."));
		list.add(new LawAttentionDto("公共安全罪的构成", "公共安全罪是指......."));
		return list;
	}
	
	private List<LawContentDto> getLawContentDtos() {
		List<LawContentDto> list = new ArrayList<>();
		list.add(new LawContentDto("中华人民共和国刑法", "第二百七十七条[8]", "内容"));
		list.add(new LawContentDto("中华人民共和国刑法", "第二百七十七条[9]", "内容"));
		list.add(new LawContentDto("中华人民共和国刑法", "第二百七十七条[10]", "内容"));
		list.add(new LawContentDto("中华人民共和国刑法", "第二百七十七条[11]", "内容"));
		list.add(new LawContentDto("中华人民共和国刑法", "第二百七十七条[12]", "内容"));
		return list;
	}
	
	private List<OldCaseDto> getOldCaseDtos() {
		List<OldCaseDto> list = new ArrayList<>();
		list.add(new OldCaseDto(100L, "case_100案号", "2014-09-07", "北京中级法院"));
		list.add(new OldCaseDto(101L, "case_101案号", "2014-09-08", "北京中级法院"));
		list.add(new OldCaseDto(102L, "case_102案号", "2014-09-09", "北京中级法院"));
		list.add(new OldCaseDto(103L, "case_103案号", "2014-09-10", "北京中级法院"));
		list.add(new OldCaseDto(104L, "case_104案号", "2014-09-11", "北京中级法院"));
		return list;
	}
	
	private List<InfluenceDto> getInfluenceDtos() {
		List<InfluenceDto> list = new ArrayList<>();
		list.add(new InfluenceDto("刑事和解", "1"));
		list.add(new InfluenceDto("民事和解", "2"));
		return list;
	}
}

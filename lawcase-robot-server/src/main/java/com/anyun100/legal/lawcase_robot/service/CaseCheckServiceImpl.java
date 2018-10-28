package com.anyun100.legal.lawcase_robot.service;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anyun100.legal.lawcase_robot.domain.AnalysisAttention;
import com.anyun100.legal.lawcase_robot.domain.AnalysisLaw;
import com.anyun100.legal.lawcase_robot.domain.CaseAnalysis;
import com.anyun100.legal.lawcase_robot.domain.Category;
import com.anyun100.legal.lawcase_robot.domain.CategoryOptionAnalysis;
import com.anyun100.legal.lawcase_robot.domain.CurrentLegalCase;
import com.anyun100.legal.lawcase_robot.domain.OldCase;
import com.anyun100.legal.lawcase_robot.domain.OldCaseAnalysis;
import com.anyun100.legal.lawcase_robot.domain.Option;
import com.anyun100.legal.lawcase_robot.domain.Option.OptionaNameValue;
import com.anyun100.legal.lawcase_robot.domain.dto.BaseCaseDto;
import com.anyun100.legal.lawcase_robot.domain.dto.CaseCategoryDto;
import com.anyun100.legal.lawcase_robot.domain.dto.CaseCategoryOptionDto;
import com.anyun100.legal.lawcase_robot.domain.dto.CategoryOptionDto;
import com.anyun100.legal.lawcase_robot.domain.dto.CurrentLegalCaseDto;
import com.anyun100.legal.lawcase_robot.domain.dto.InfluenceDetailDto;
import com.anyun100.legal.lawcase_robot.domain.dto.InfluenceDto;
import com.anyun100.legal.lawcase_robot.domain.dto.LawAttentionDto;
import com.anyun100.legal.lawcase_robot.domain.dto.LawContentDto;
import com.anyun100.legal.lawcase_robot.domain.dto.OldCaseDto;
import com.anyun100.legal.lawcase_robot.domain.dto.OptionSearchDto;
import com.anyun100.legal.lawcase_robot.mapper.AnalysisAttentionMapper;
import com.anyun100.legal.lawcase_robot.mapper.AnalysisLawMapper;
import com.anyun100.legal.lawcase_robot.mapper.CaseAnalysisMapper;
import com.anyun100.legal.lawcase_robot.mapper.CategoryOptionAnalysisMapper;
import com.anyun100.legal.lawcase_robot.mapper.OldCaseAnalysisMapper;
import com.anyun100.legal.lawcase_robot.mapper.OldCaseMapper;
import com.anyun100.legal.lawcase_robot.util.DateTimeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

@Service
public class CaseCheckServiceImpl implements CaseCheckService {
	
	@Autowired
	private LegalCaseStore legalCaseStore;
	
	@Autowired
	private PageProperties pageProperties;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private CaseAnalysisMapper caseAnalysisMapper;
	
	@Autowired
	private CategoryOptionAnalysisMapper categoryOptionAnalysisMapper;
	
	@Autowired
	private OldCaseAnalysisMapper OldCaseAnalysisMapper;
	
	@Autowired
	private OldCaseMapper oldCaseMapper;
	
	@Autowired
	private AnalysisLawMapper analysisLawMapper;
	
	@Autowired
	private AnalysisAttentionMapper analysisAttentionMapper;

	public BaseCaseDto getExistingCases(String openId) {
		List<CurrentLegalCase> currentLegalCases = legalCaseStore.getCurrentLegalCaseStore().get(openId);
		if (currentLegalCases != null && getValidCurrentLegalCase(currentLegalCases) != null) {
			List<CurrentLegalCase> validCurrentLegalCases = getValidCurrentLegalCase(currentLegalCases);
			Collections.sort(validCurrentLegalCases);
			CurrentLegalCase currentLegalCase = validCurrentLegalCases.get(0);
			long case_count = validCurrentLegalCases.size();
			return new BaseCaseDto(0,
					ImmutableMap.of("case_id", currentLegalCase.getCaseId(), "court_datetime",
							currentLegalCase.getCourt_datetime(), "case_num", currentLegalCase.getCase_num(), "case_count",
							case_count, "case_name", currentLegalCase.getCase_name()));
		}
		return new BaseCaseDto(0, ImmutableMap.of());

	}

	public BaseCaseDto isCaseExisted(String openId) {
		List<CurrentLegalCase> currentLegalCases = legalCaseStore.getCurrentLegalCaseStore().get(openId);
		if (currentLegalCases != null && getValidCurrentLegalCase(currentLegalCases) != null) {
			List<CurrentLegalCaseDto> validCurrentLegalCases = getValidCurrentLegalCaseDtos(currentLegalCases);
			long case_count = validCurrentLegalCases.size();
			return (case_count > 0 ? new BaseCaseDto(0, ImmutableMap.of("exists", 1))
					: new BaseCaseDto(0, ImmutableMap.of("exists", 0)));
		}
		return new BaseCaseDto(0, ImmutableMap.of("exists", 0));
	}
	
	public BaseCaseDto listCasesExisted(String openId) {
		List<CurrentLegalCase> currentLegalCases = legalCaseStore.getCurrentLegalCaseStore().get(openId);
		if (currentLegalCases != null && getValidCurrentLegalCase(currentLegalCases) != null) {
			List<CurrentLegalCaseDto> validCurrentLegalCases = getValidCurrentLegalCaseDtos(currentLegalCases);
			Collections.sort(validCurrentLegalCases);
			long case_count = validCurrentLegalCases.size();
			long pageCount = Math.round(Math.ceil((double)case_count/(double)pageProperties.getDefaultPageSize()));  
			
			return new BaseCaseDto(0, ImmutableMap.of("page_count", pageCount, "page_num", 1,  "count", case_count,
									"results", (validCurrentLegalCases != null ? validCurrentLegalCases.toArray() : "")));
		}
		return new BaseCaseDto(0, ImmutableMap.of());			
	}
	
	public BaseCaseDto getCaseCategory(String case_id) {
		
		return new BaseCaseDto(0, ImmutableMap.of("results", getCaseCategoryDto().toArray()));
		
	}
	
	public JSONObject getCaseCategoryJSONObject(String caseId) {
		CaseAnalysis caseAnalysis = caseAnalysisMapper.getCaseAnalysis(caseId);
		try {
				if (caseAnalysis != null && categoryOptionAnalysisMapper.getCategoryId(caseAnalysis.getAnalysisId()) != null) {
					return getCaseCategory(categoryOptionAnalysisMapper.getCategoryId(caseAnalysis.getAnalysisId()), caseAnalysis.getAnalysisId());
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("error_code", 0);
		json.put("data", "");
		return json;
	}
	
	public BaseCaseDto analysisSimilarcase(String caseId, int page, int pageCount) {
		if (caseAnalysisMapper.getCaseAnalysis(caseId) != null ) {
		Long analysisId = caseAnalysisMapper.getCaseAnalysis(caseId).getAnalysisId();
		List<OldCaseAnalysis> oldCaseAnalysises = OldCaseAnalysisMapper.getOldCaseAnalysis(analysisId);
		List<Long> oldCaseId = oldCaseAnalysises.stream().map(OldCaseAnalysis :: getOldCaseId).collect(Collectors.toList());
		List<OldCase> oldCases = oldCaseMapper.getOldCases(oldCaseId);
		List<OldCaseDto> oldCaseDtos = new ArrayList<>();
		oldCases.stream().forEach(o -> oldCaseDtos.add(new OldCaseDto(o.getOldCaseId(), o.getCaseNum(), o.getCaseDate(), o.getCourt())));
			if (!oldCases.isEmpty()) {
				return new BaseCaseDto(0, ImmutableMap.<String, Serializable>builder()
									.put("case_id", caseId)
									.put("count", oldCaseDtos.size())
									.put("page", page)
									.put("page_count", pageCount)
									.put("results", oldCaseDtos.toArray()).build());
			}
		}
		//TODO: fake return to be removed
		return new BaseCaseDto(0, ImmutableMap.<String, Serializable>builder()
				.put("case_id", caseId)
				.put("count", getOldCaseDtos().size())
				.put("page", page)
				.put("page_count", pageCount)
				.put("results", getOldCaseDtos().toArray()).build());
	}
	
	public BaseCaseDto getAnalysisLaw(String caseId) {
		if (caseAnalysisMapper.getCaseAnalysis(caseId) != null ) {
			Long anaLysisId = caseAnalysisMapper.getCaseAnalysis(caseId).getAnalysisId();
			List<AnalysisLaw> analysisLaws = analysisLawMapper.getAnalysisLaws(anaLysisId);
			List<LawContentDto> lawContentDtos = new ArrayList<>();
			if (!analysisLaws.isEmpty()) {
				analysisLaws.stream().forEach(a -> lawContentDtos.add(new LawContentDto(a.getLawName(), a.getLawNum(), a.getLawContent())));
				return new BaseCaseDto(0, ImmutableMap.<String, Serializable>builder()
						.put("case_id", caseId)
						.put("count", lawContentDtos.size())
						.put("results", lawContentDtos.toArray()).build());
				
			}
		}
		//TODO: fake retrun to be removed
		return new BaseCaseDto(0, ImmutableMap.<String, Serializable>builder()
				.put("case_id", caseId)
				.put("count", getLawContentDtos().size())
				.put("results", getLawContentDtos().toArray()).build());
	}
	
	public BaseCaseDto getCaseContent(String caseId) {
		List<CurrentLegalCase> currentLegalCases = legalCaseStore.getCurrentLegalCaseStore().values().stream().flatMap(List :: stream).collect(Collectors.toList());
		Optional<CurrentLegalCase> currentLegalCase = currentLegalCases.stream().filter(c -> c.getCaseId().equals(caseId)).findFirst();
		if (currentLegalCase.isPresent()) {
			String contents = currentLegalCase.get().getCase_content() != null 
					&& !currentLegalCase.get().getCase_content().isEmpty() ? currentLegalCase.get().getCase_content() : getCaseFileContent(caseId);
					
			return new BaseCaseDto(0,
					ImmutableMap.of("caseid", currentLegalCase.get().getCaseId(), "case_num", currentLegalCase.get().getCase_num(), 
							"case_content", contents, "case_datetime", currentLegalCase.get().getCourt_datetime()));
		}
		return new BaseCaseDto(0, ImmutableMap.of());	
	}
	
	public BaseCaseDto getAnalysisAttention(String caseId) {
		if (caseAnalysisMapper.getCaseAnalysis(caseId) != null ) {
			Long analysisId = caseAnalysisMapper.getCaseAnalysis(caseId).getAnalysisId();
			List<AnalysisAttention> analysisAttentions = analysisAttentionMapper.getAnalysisAttentions(analysisId);
			List<LawAttentionDto> lawAttentionDtos = new ArrayList<>();
			if (!analysisAttentions.isEmpty()) {
				analysisAttentions.stream().forEach(a -> lawAttentionDtos.add(new LawAttentionDto(a.getAttentionName(), a.getAttentionContent())));
				return new BaseCaseDto(0, ImmutableMap.<String, Serializable>builder()
						.put("case_id", caseId)
						.put("count", lawAttentionDtos.size())
						.put("results", lawAttentionDtos.toArray()).build());
			}
		}
		//fake return to be removed
		return new BaseCaseDto(0, ImmutableMap.<String, Serializable>builder()
				.put("case_id", caseId)
				.put("count", getLawAttentionDto().size())
				.put("results", getLawAttentionDto().toArray()).build());
	}
	
	public BaseCaseDto getAnalysisInfluence(String case_id) {
		return new BaseCaseDto(0, ImmutableMap.<String, Serializable>builder()
				.put("case_id", case_id)
				.put("influence_intro", "系统分析了16个要素.........")
				.put("results", getInfluenceDtos().toArray()).build());
	}
	
	public JSONObject getAnalysisInfluencedetail(Long influence_id) {
		try {
			JSONObject json = new JSONObject();
			json.put("error_code", 0);
			json.put("data", getInfluenceDetailDto());
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	private JSONObject getCaseCategory(Long categoryId, Long analysisId) throws Exception {
		String options = categoryOptionAnalysisMapper.getOptions(analysisId);
		ObjectMapper mapper = new ObjectMapper();
		OptionSearchDto[] optionSearchDtos = mapper.readValue(options, OptionSearchDto[].class);
		List<CategoryOptionDto> categoryOptionDtos = new ArrayList<>();
		if (optionSearchDtos != null) {
			Arrays.stream(optionSearchDtos).forEach(o -> categoryOptionDtos.add(
					new CategoryOptionDto(Option.getOption(o.getOption_id()).getOption_name(), 
										  Option.getOption(o.getOption_id()).getOptions().stream()
										  .filter(_o -> _o.getValue().equals(String.valueOf(o.getValue())))
										  .map(OptionaNameValue::getName).findAny().orElse(""),
										  o.getValue().intValue())));
			CaseCategoryOptionDto caseCategoryOptionDto = new CaseCategoryOptionDto(categoryId, Category.getCategory(categoryId).getCategory_name(),
															  Category.getCategory(Category.getCategory(categoryId).getParent_category_id()).getCategory_name(),
															  categoryOptionDtos);
			JSONObject json = new JSONObject();
			json.put("error_code", 0);
			json.put("data", caseCategoryOptionDto);
			return json;
		}
		JSONObject json = new JSONObject();
		json.put("error_code", 0);
		json.put("data", "");
		return json;
	}
	
	private InfluenceDetailDto getInfluenceDetailDto() {
		return new InfluenceDetailDto("11", false, "22", "28", "33", "39", "24.0", true, "60", "36");
	}
	private List<InfluenceDto> getInfluenceDtos() {
		List<InfluenceDto> list = new ArrayList<>();
		list.add(new InfluenceDto("刑事和解", "1"));
		list.add(new InfluenceDto("民事和解", "2"));
		return list;
	}
	
	private List<LawAttentionDto> getLawAttentionDto() {
		List<LawAttentionDto> list = new ArrayList<>();
		list.add(new LawAttentionDto("妨害公务罪的构成", "妨害公务罪是指......."));
		list.add(new LawAttentionDto("公共安全罪的构成", "公共安全罪是指......."));
		return list;
	}
	
	private String getCaseFileContent(String case_id) {
		StringBuffer out = new StringBuffer();
	     byte[] b = new byte[4096];
	     try {
		     for (int n; (n = storageService.loadAsResource(case_id).getInputStream().read(b)) != -1;) {
		          out.append(new String(b, 0, n));
		     }
	     	
	     } catch(IOException e) {
	    	 e.printStackTrace();
	     }
	     return out.toString();
		
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
	
	private List<LawContentDto> getLawContentDtos() {
		List<LawContentDto> list = new ArrayList<>();
		list.add(new LawContentDto("中华人民共和国刑法", "第二百七十七条[8]", "内容"));
		list.add(new LawContentDto("中华人民共和国刑法", "第二百七十七条[9]", "内容"));
		list.add(new LawContentDto("中华人民共和国刑法", "第二百七十七条[10]", "内容"));
		list.add(new LawContentDto("中华人民共和国刑法", "第二百七十七条[11]", "内容"));
		list.add(new LawContentDto("中华人民共和国刑法", "第二百七十七条[12]", "内容"));
		return list;
	}
	
	private List<CaseCategoryDto> getCaseCategoryDto() {
		List<CaseCategoryDto> list = new ArrayList<>();
		list.add(new CaseCategoryDto(Category.DUTY_CRIME.getCategory_id(), Category.DUTY_CRIME.getCategory_name(), 
				Category.getCategory(Category.DUTY_CRIME.getParent_category_id()).getCategory_name()));
		list.add(new CaseCategoryDto(Category.BLACKMAIL.getCategory_id(), Category.BLACKMAIL.getCategory_name(), 
				Category.getCategory(Category.BLACKMAIL.getParent_category_id()).getCategory_name()));
		list.add(new CaseCategoryDto(Category.DISRUPTING_PUBLIC_SERVICE.getCategory_id(), Category.DISRUPTING_PUBLIC_SERVICE.getCategory_name(), 
				Category.getCategory(Category.DISRUPTING_PUBLIC_SERVICE.getParent_category_id()).getCategory_name()));
		list.add(new CaseCategoryDto(Category.OPENING_GAMBLING.getCategory_id(), Category.OPENING_GAMBLING.getCategory_name(), 
				Category.getCategory(Category.OPENING_GAMBLING.getParent_category_id()).getCategory_name()));
		return list;
	}
	
	private List<CurrentLegalCase> getValidCurrentLegalCase(List<CurrentLegalCase> currentLegalCases) {
		List<CurrentLegalCase> validCurrentLegalCases = currentLegalCases.stream()
				.filter(c -> DateTimeUtil.parseLocalDateTime(c.getCourt_datetime()).isAfter(LocalDateTime.now())).collect(Collectors.toList());
		return validCurrentLegalCases;
	}
	
	private List<CurrentLegalCaseDto> getValidCurrentLegalCaseDtos(List<CurrentLegalCase> currentLegalCases) {
		List<CurrentLegalCase> validCurrentLegalCases = currentLegalCases.stream()
				.filter(c -> DateTimeUtil.parseLocalDateTime(c.getCourt_datetime()).isAfter(LocalDateTime.now())).collect(Collectors.toList());
		List<CurrentLegalCaseDto> currentLegalCaseDtos = new ArrayList<>();
		validCurrentLegalCases.stream().forEach(v -> currentLegalCaseDtos.add(new CurrentLegalCaseDto(v.getCaseId(), v.getCase_num(), v.getCase_content(), v.getCourt_datetime())));
		return currentLegalCaseDtos;
	}
}

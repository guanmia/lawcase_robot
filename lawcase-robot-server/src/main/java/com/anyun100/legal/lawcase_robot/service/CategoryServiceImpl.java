package com.anyun100.legal.lawcase_robot.service;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

import com.anyun100.legal.lawcase_robot.domain.Category;
import com.anyun100.legal.lawcase_robot.domain.Option;
import com.anyun100.legal.lawcase_robot.domain.dto.BaseCaseDto;
import com.anyun100.legal.lawcase_robot.domain.dto.CategoryDto;
import com.anyun100.legal.lawcase_robot.domain.dto.OptionDto;
import com.anyun100.legal.lawcase_robot.domain.dto.ParentChildCategoryDto;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final Map<String, Serializable> data = new HashMap<>();
	public List<ParentChildCategoryDto> parentChildCategoryDtos = new ArrayList<>();
	public BaseCaseDto contructCategories(Long parentid) {
		
		if (parentid != null && parentid != 0) {
			parentChildCategoryDtos.clear();
			getParentChildCategoryDtos(parentid);
			Collections.sort(parentChildCategoryDtos);
			data.clear();
			data.put("results",parentChildCategoryDtos.toArray());
		} else {
			parentChildCategoryDtos.clear();
			getParentChildCategoryDtos((long) 1);
			Collections.sort(parentChildCategoryDtos);
			data.clear();
			data.put("results", parentChildCategoryDtos.toArray());
		}
		return new BaseCaseDto(0, data);
		
	}
	public BaseCaseDto getOptions(Long category_id) {
		data.clear();
		data.put("results", getOptionDto(category_id).toArray());
		return new BaseCaseDto(0, data);
	}
	
	private List<OptionDto> getOptionDto(long category_id) {
		List<OptionDto> optionDtos = new ArrayList<>();
		for (Option option : Category.getOptions(category_id)) {
			OptionDto optionDto = new OptionDto();
			optionDto.setOption_id(option.getOption_id());
			optionDto.setOption_name(option.getOption_name());
			optionDto.setOptions(option.getOptions());
			optionDto.setStep(option.getStep());
			optionDto.setType(option.getType());
			optionDtos.add(optionDto);
		}
		Collections.sort(optionDtos);
		return optionDtos;
	}
	

	private List<CategoryDto> getCategoryDtos(Set<Category> categories) {
		List<CategoryDto> categoryDtos = new ArrayList<>();
		for (Category category : categories) {
			CategoryDto categoryDto = new CategoryDto();
			categoryDto.setCategory_id(category.getCategory_id());
			categoryDto.setCategory_name(category.getCategory_name());
			categoryDto.setParentid(category.getParent_category_id());
			categoryDtos.add(categoryDto );
		}
		return categoryDtos;
	}
	
	
	private List<ParentChildCategoryDto> getParentChildCategoryDtos(Long parentid) {
		Set<Category> childCategories = Category.getChildCategories(parentid);
		Category parentCategory = Category.getCategory(parentid);
		if (!childCategories.isEmpty()) {
			List<CategoryDto> childCategoryDtos = getCategoryDtos(childCategories);
			Collections.sort(childCategoryDtos);
			ParentChildCategoryDto parentChildCategoryDto = new ParentChildCategoryDto();
			parentChildCategoryDto.setCategory_id(parentCategory.getCategory_id());
			parentChildCategoryDto.setCategory_name(parentCategory.getCategory_name());
			parentChildCategoryDto.setParentid(parentCategory.getParent_category_id());
			parentChildCategoryDto.setChildren(childCategoryDtos);
			for (Category category : childCategories) {
				Long _parentid = category.getCategory_id();
				if (!Category.getChildCategories(_parentid).isEmpty()) {
					getParentChildCategoryDtos(_parentid);
				}
			}
			if (!parentChildCategoryDtos.contains(parentChildCategoryDto)) {
				parentChildCategoryDtos.add(parentChildCategoryDto);
			}
			return parentChildCategoryDtos;
		}
		ParentChildCategoryDto parentChildCategoryDto = new ParentChildCategoryDto();
		parentChildCategoryDto.setCategory_id(parentCategory.getCategory_id());
		parentChildCategoryDto.setCategory_name(parentCategory.getCategory_name());
		parentChildCategoryDto.setParentid(parentCategory.getParent_category_id());
		parentChildCategoryDtos.add(parentChildCategoryDto);
		return parentChildCategoryDtos;
	}
	
	private ParentChildCategoryDto getParentChildCategoryDto(Long parentid) {
		Set<Category> childCategories = Category.getChildCategories(parentid);
		Category parentCategory = Category.getCategory(parentid);
		if (!childCategories.isEmpty()) {
			List<CategoryDto> childCategoryDtos = getCategoryDtos(childCategories);
			Collections.sort(childCategoryDtos);
			ParentChildCategoryDto parentChildCategoryDto = new ParentChildCategoryDto();
			parentChildCategoryDto.setCategory_id(parentCategory.getCategory_id());
			parentChildCategoryDto.setCategory_name(parentCategory.getCategory_name());
			parentChildCategoryDto.setParentid(parentCategory.getParent_category_id());
			parentChildCategoryDto.setChildren(childCategoryDtos);
			parentChildCategoryDtos.add(parentChildCategoryDto);
			return parentChildCategoryDto;
		}
		ParentChildCategoryDto parentChildCategoryDto = new ParentChildCategoryDto();
		parentChildCategoryDto.setCategory_id(parentCategory.getCategory_id());
		parentChildCategoryDto.setCategory_name(parentCategory.getCategory_name());
		parentChildCategoryDto.setParentid(parentCategory.getParent_category_id());
		parentChildCategoryDtos.add(parentChildCategoryDto);
		return parentChildCategoryDto;
	}

}

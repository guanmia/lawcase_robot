package com.anyun100.legal.lawcase_robot.service;


import com.anyun100.legal.lawcase_robot.domain.dto.BaseCaseDto;

public interface CategoryService {
	
	BaseCaseDto contructCategories(Long parentid);
	BaseCaseDto getOptions(Long category_id);

}

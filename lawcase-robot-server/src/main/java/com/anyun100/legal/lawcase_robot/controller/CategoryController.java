package com.anyun100.legal.lawcase_robot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anyun100.legal.lawcase_robot.domain.dto.BaseCaseDto;
import com.anyun100.legal.lawcase_robot.service.CategoryService;

@RestController
@RequestMapping("/lawrobot/api/casecategory")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public BaseCaseDto listCategories(@RequestParam(value = "parentid", required = false) Long parentid) {
		return categoryService.contructCategories(parentid);
	}
	
	@RequestMapping(value = "/searchoptions", method = RequestMethod.POST)
	@ResponseBody
	public BaseCaseDto searchoptions(@RequestParam(value = "category_id", required = true) Long category_id) {
		return categoryService.getOptions(category_id);
	}

}

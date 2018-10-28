package com.anyun100.legal.lawcase_robot.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CategoryUnitTest {

	@Test
	public void TestCategoryList() {
		int size = Category.getChildCategories(0).size();
		assertThat(size == 1);
		long id = Category.CRIME.getParent_category_id();
		assertThat(Category.CRIME.getCategory_id() == 1);
	}
}

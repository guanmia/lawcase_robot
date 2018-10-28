package com.anyun100.legal.lawcase_robot.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;
import com.google.common.collect.ImmutableList;
import com.anyun100.legal.lawcase_robot.domain.Option;

public enum Category {

	CRIME(1, "刑事案件", 0, Collections.emptyList()), 
	PROPERTY_SAFETY(2, "财产安全", 1, Collections.emptyList()), 
	PERSONAL_SAFETY(3, "人身安全", 1, Collections.emptyList()), 
	TRAFFIC_SAFETY(4, "交通安全",1, Collections.emptyList()),
	PUBLIC_SAFETY(5, "公共安全", 1, Collections.emptyList()), 
	DUTY_CRIME(6, "职务犯罪", 1, Collections.emptyList()), 
	DRUG_CRIME(7, "毒品相关", 1, Collections.emptyList()), 
	EMBEZZLEMENT(8, "盗窃",2, ImmutableList.of(Option.EMBEZZLEMENT, Option.EMBEZZLEMENT_MONEY)), 
	ROBBERTY(9, "抢劫", 2,  ImmutableList.of(Option.ROBBERTY)), 
	LOOT(10, "抢夺", 2, ImmutableList.of(Option.LOOT)), 
	FRAUD(11, "诈骗", 2,  ImmutableList.of(Option.FRAUD_AMOUNNT)), 
	BLACKMAIL(12, "敲诈勒索", 2, ImmutableList.of(Option.BLACKMAIL, Option.BLACKMAIL_MONEY)),
	CONTRACT_FRAUD(13, "合同诈骗", 2, ImmutableList.of(Option.CONTRACT_FRAUD_AMOUNT)),
	INTENTIONAL_INJURY(14, "故意伤害", 3, ImmutableList.of(Option.INTENTIONAL_INJURY_MINOR, Option.INTENTIONAL_INJURY_SEVERE, Option.INTENTIONAL_INJURY_MONEY)),
	UNLAWFUL_DETENTION(15, "非法拘禁", 3, ImmutableList.of(Option.UNLAWFUL_DETENTION_PEOPLE, Option.UNLAWFUL_DETENTION_DAYS)),
	RAPE(16, "强奸", 3, ImmutableList.of(Option.RAPE_WOMEN, Option.RAPE_YOUTH)),
	TRAFFIC_OFFENCE(17, "交通肇事", 4, ImmutableList.of(Option.TRAFFIC_OFFENCE_SEVERE, Option.TRAFFIC_OFFENCE_DEATH, Option.TRAFFIC_OFFENCE_LIABILITY)),
	DANGER_DRIVING(18, "危险驾驶", 4, ImmutableList.of(Option.TRAFFIC_OFFENCE_LIABILITY)),
	DISRUPTING_PUBLIC_SERVICE(19, "妨害公务", 5, ImmutableList.of(Option.DISRUPTING_PUBLIC_SERVICE_INJURE_AMOUNT)), 
	OPENING_GAMBLING(20, "开设赌场", 5, Collections.emptyList()),
	AFFRAY(21, "聚众斗殴", 5, ImmutableList.of(Option.AFFRAY_AMOUNT)),
	GAMBLING(22, "赌博", 5, Collections.emptyList()),
	DERELICT(23, "玩忽职守", 6, ImmutableList.of(Option.DERELICT_DEATH)), 
	BRIBERY(24, "行贿", 6, ImmutableList.of(Option.BRIBERY_AMOUNT)),
	BRIBE_TAKING(25, "受贿", 6, ImmutableList.of(Option.BRIBE_TAKING_AMOUNT)),
	CORRUPTION(26, "贪污", 6,  ImmutableList.of(Option.CORRUPTION_AMOUNT)),
	MAKE_SALE_TRASPORT(27, " 制造贩卖运输", 7, ImmutableList.of(Option.DRUG_AMOUT, Option.DRUG_DEAL_TIMES)),
	UNLAWFUL_HOLDING(28, "非法持有", 7, ImmutableList.of(Option.HARD_DRUG_UNLAWFUL_HOLDING_AMOUNT, Option.SOFT_DRUG_UNLAWFUL_HOLDING_AMOUNT)),
	ACCOMMODATE_THIRD_PARTY(29, "容留他人", 7, Collections.emptyList());

	private long category_id;
	private String category_name;
	private long parent_category_id;
	private List<Option> options;


	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public void setParent_category_id(long parent_category_id) {
		this.parent_category_id = parent_category_id;
	}

	public long getCategory_id() {
		return category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public long getParent_category_id() {
		return parent_category_id;
	}

	Category(long category_id, String category_name, long parent_category_id, List<Option> options) {
		this.category_id = category_id;
		this.category_name = category_name;
		this.parent_category_id = parent_category_id;
		this.options = options;
	}

	public static Set<Category> getChildCategories(long category_id) {
		Set<Category> childCategories = new HashSet<>();
		for (Category category : Category.values()) {
			if (category.parent_category_id == category_id) {
				childCategories.add(category);
			}
		}
		return childCategories;
	}

	public static Category getCategory(Long category_id) {
		for (Category category : Category.values()) {
			if (category.category_id == category_id) {
				return category;
			}
		}
		return null;
	}
	
	public static List<Option> getOptions(Long category_id) {
		for (Category category : Category.values()) {
			if (category.category_id == category_id) {
				return category.options;
			}
		}
		return null;
	}
}

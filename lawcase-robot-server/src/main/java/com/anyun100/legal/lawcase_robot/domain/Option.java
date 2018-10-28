package com.anyun100.legal.lawcase_robot.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableList;
import java.util.Collections;

public enum Option implements Serializable{
	
	EMBEZZLEMENT(1, "盗窃次数",getOptionList("1-2",  "1", "大于3", "3"), 0, "select"),
	ROBBERTY(2, "抢劫次数",  getOptionList("1-2", "1", "大于3", "3"), 0, "select"),
	LOOT(3, "抢夺次数", getOptionList("1-2", "1", "3-5", "3", "大于5", "5"), 0, "select"),
	BLACKMAIL(4, "敲诈次数", getOptionList("1-2", "1", "3-5", "3", "大于5", "5"), 0, "select"),
	INTENTIONAL_INJURY_MINOR(5, "轻伤总人数", getOptionList("1", "1", "2", "2"), 0, "select"),
	INTENTIONAL_INJURY_SEVERE(6, "重伤总人数", getOptionList("1", "1", "2", "2"), 0, "select"),
	INTENTIONAL_INJURY_MONEY(7, "重伤总人数", getOptionList("1", "1", "2", "2"), 0, "select"),
	UNLAWFUL_DETENTION_DAYS(8, "非法拘禁天数", getOptionList("1-2", "1", "大于3", "3"), 0, "select"),
	UNLAWFUL_DETENTION_PEOPLE(9, "非法拘禁人数", getOptionList("1-2", "1", "3-5", "3", "大于5", "5"), 0, "select"),
	RAPE_WOMEN(10, "强奸妇女人数", getOptionList("1", "1", "2", "2"), 0, "select"),
	RAPE_YOUTH(11, "强奸幼女人数", getOptionList("1", "1", "2", "2"), 0, "select"),
	TRAFFIC_OFFENCE_SEVERE(12, "重伤人数",  getOptionList("1", "1", "2-3", "2", "大于3", "3"), 0, "select"),
	TRAFFIC_OFFENCE_DEATH(13, "死亡人数",  getOptionList("1", "1", "2-3", "2", "大于3", "3"), 0, "select"),
	TRAFFIC_OFFENCE_LIABILITY(14, "事故责任",  getOptionList("主要责任", "1", "全部责任", "2", "同等责任", "3"), 0, "select"),
	EMBEZZLEMENT_MONEY(15, "盗窃金额", Collections.emptyList(), 2000, "int"),
	BLACKMAIL_MONEY(16, "违法所得", Collections.emptyList(), 5000, "int"),
	DRUG_AMOUT(17, "海洛因、冰毒等重量（克）",  Collections.emptyList(), 1000, "int"),
	DRUG_DEAL_TIMES(18, "贩卖毒品次数（次）",  getOptionList("1-2", "1", "3-5", "3", "大于5", "5"), 0, "select"),
	HARD_DRUG_UNLAWFUL_HOLDING_AMOUNT(19, "海洛因(白粉)、冰毒、可卡因重量（克）",  Collections.emptyList(), 1000, "int"),
	SOFT_DRUG_UNLAWFUL_HOLDING_AMOUNT(20, "鸦片、K粉、美沙酮重量（克）",  getOptionList("100-200", "100", "200-400", "200", "400-600", "400", "600-1000", "600", "1000-1500", "1000"), 0, "select"),
	FRAUD_AMOUNNT(21, "诈骗数额（元）", Collections.emptyList(), 100000, "int"),
	DISRUPTING_PUBLIC_SERVICE_INJURE_AMOUNT(22, "致轻微伤人数（人)", getOptionList("1-2", "1", "3-5", "3", "5-7", "5", "大于7", "7"), 0, "select"),
	AFFRAY_AMOUNT(23, "双方参与人数（人", getOptionList("4-8", "4", "8-20", "8", "20-40", "20", "大于40", "40"), 0, "select"),
	DERELICT_DEATH(24, "造成死亡人数",  getOptionList("1", "1", "2-3", "2", "大于3", "3"), 0, "select"),
	BRIBERY_AMOUNT(25, "行贿数额（元）", Collections.emptyList(), 100000, "int"),
	BRIBE_TAKING_AMOUNT(26, "受贿数额（元）", Collections.emptyList(), 100000, "int"),
	CORRUPTION_AMOUNT(27, "贪污数额（元）",  Collections.emptyList(), 100000, "int"),
	CONTRACT_FRAUD_AMOUNT(28, "合同诈骗数额（元）", Collections.emptyList(), 100000, "int");
	private long option_id;
	private String option_name;
	private List<OptionaNameValue> options;
	private int step;
	private String type;
	
	Option(long option_id, String option_name, List<OptionaNameValue> options, int step, String type) {
		this.option_id = option_id;
		this.option_name = option_name;
		this.options = options;
		this.step = step;
		this.type = type;
	}
	
	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public long getOption_id() {
		return option_id;
	}

	public String getOption_name() {
		return option_name;
	}

	public List<OptionaNameValue> getOptions() {
		return options;
	}

	public String getType() {
		return type;
	}

	public void setOption_id(long option_id) {
		this.option_id = option_id;
	}

	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}

	public void setOptions(List<OptionaNameValue> options) {
		this.options = options;
	}

	public void setType(String type) {
		this.type = type;
	}

	//Visiblefortesting
	public static List<OptionaNameValue> getOptionList(String...pairs) {
		List<OptionaNameValue> list = new ArrayList<>();
		for (int i = 0; i < pairs.length; i += 2) {
			OptionaNameValue optionaNameValue = new OptionaNameValue();
			optionaNameValue.setId(i == 0 ? i : i - 1);
			optionaNameValue.setName(pairs[i]);
			optionaNameValue.setValue(pairs[i+1]);
			list.add(optionaNameValue);
		}
		return list;
	}
	
	public static Option getOption(Long option_id) {
		for (Option option : Option.values()) {
			if (option.option_id == option_id) {
				return option;
			}
		}
		return null;
	}
	
	public static class OptionaNameValue {
		private int id;
		private String name;
		private String value;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public String getValue() {
			return value;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
	}

}

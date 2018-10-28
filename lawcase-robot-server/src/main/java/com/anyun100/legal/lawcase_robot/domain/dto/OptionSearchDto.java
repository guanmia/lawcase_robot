package com.anyun100.legal.lawcase_robot.domain.dto;

public class OptionSearchDto {
	
	private Long option_id;
	private Long value;
	private String type;
	public Long getOption_id() {
		return option_id;
	}
	public Long getValue() {
		return value;
	}
	public String getType() {
		return type;
	}
	public void setOptionId(Long option_id) {
		this.option_id = option_id;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean equals(OptionSearchDto o) {  
        if(o == null) return false;  
        if(this == o) return true;  
        if(option_id == o.option_id  && value == o.value  && type.equals(o.type)) {
            return true;
        }
        //Just to assume this value within five time values of the other, they are still equal
        if(option_id == o.option_id   && type.equals(o.type) && (o.type.equals("int") && 0 < value && value < o.value * 5)) {
            return true;
        }
        return false;  
    }  
      
    @Override  
    public int hashCode() {  
      int result = 17;  
      result = 31*result + (option_id != null ? option_id.hashCode() : 0);  
      result = 31*result + (value != null ? value.hashCode() : 0);  
      result = 31*result + type != null ? type.hashCode()  :0;  
      return result;  
    }  
}

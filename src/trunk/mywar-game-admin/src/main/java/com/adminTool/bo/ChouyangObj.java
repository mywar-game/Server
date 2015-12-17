package com.adminTool.bo;

public class ChouyangObj {

	private String endRegDateStr;
	private Integer day;
	
	private Float totalConsume = 0.0f;
	private Integer totalCreateUser = 0;
	private float ltv = 0.0f;
	
	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getEndRegDateStr() {
		return endRegDateStr;
	}

	public void setEndRegDateStr(String endRegDateStr) {
		this.endRegDateStr = endRegDateStr;
	}

	public Float getTotalConsume() {
		return totalConsume;
	}

	public void setTotalConsume(Float totalConsume) {
		this.totalConsume = totalConsume;
	}

	public Integer getTotalCreateUser() {
		return totalCreateUser;
	}

	public void setTotalCreateUser(Integer totalCreateUser) {
		this.totalCreateUser = totalCreateUser;
	}

	public float getLtv() {
		return ltv;
	}

	public void setLtv(float ltv) {
		this.ltv = ltv;
	}

	public ChouyangObj() {}
	
}

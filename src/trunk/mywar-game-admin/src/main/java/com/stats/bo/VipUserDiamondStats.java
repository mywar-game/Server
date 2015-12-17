package com.stats.bo;

import java.util.Date;

public class VipUserDiamondStats {

	private int id;
	private Date date;
	private int sysNum;
	private int count;
	private int type;
	private int diamond;
	private int caterory;
	private int actityCount;
	
	public int getActityCount() {
		return actityCount;
	}

	public void setActityCount(int actityCount) {
		this.actityCount = actityCount;
	}

	public int getCaterory() {
		return caterory;
	}

	public void setCaterory(int caterory) {
		this.caterory = caterory;
	}

	public VipUserDiamondStats() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSysNum() {
		return sysNum;
	}
	public void setSysNum(int sysNum) {
		this.sysNum = sysNum;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDiamond() {
		return diamond;
	}
	public void setDiamond(int diamond) {
		this.diamond = diamond;
	}
	
}

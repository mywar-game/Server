package com.stats.bo;

import java.util.Date;

public class EquipStats {

	public EquipStats() {}
	
	private int id;
	private int allTotal;
	private int coinJoinTotal;
	private int diamondJoinTotal;
	private int coinTotal;
	private int diamondTotal;
	private int sysNum;
	private Date time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAllTotal() {
		return allTotal;
	}
	public void setAllTotal(int allTotal) {
		this.allTotal = allTotal;
	}
	public int getCoinJoinTotal() {
		return coinJoinTotal;
	}
	public void setCoinJoinTotal(int coinJoinTotal) {
		this.coinJoinTotal = coinJoinTotal;
	}
	public int getDiamondJoinTotal() {
		return diamondJoinTotal;
	}
	public void setDiamondJoinTotal(int diamondJoinTotal) {
		this.diamondJoinTotal = diamondJoinTotal;
	}
	public int getCoinTotal() {
		return coinTotal;
	}
	public void setCoinTotal(int coinTotal) {
		this.coinTotal = coinTotal;
	}
	public int getDiamondTotal() {
		return diamondTotal;
	}
	public void setDiamondTotal(int diamondTotal) {
		this.diamondTotal = diamondTotal;
	}
	public int getSysNum() {
		return sysNum;
	}
	public void setSysNum(int sysNum) {
		this.sysNum = sysNum;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}

package com.stats.bo;

import java.util.Date;

/**
 * 天界统计
 * @author Administrator
 *
 */
public class CompetitionStats {

	public CompetitionStats() {}
	
	private int id;
	private int sysNum;
	private Date time;
	private int joinTotal; // 参与人数
	private int diamondUse; // 钻石消耗总数
	private int diamondJoin; // 钻石消耗人数
	private int coinUse; // 金币消耗总数
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getJoinTotal() {
		return joinTotal;
	}
	public void setJoinTotal(int joinTotal) {
		this.joinTotal = joinTotal;
	}
	public int getDiamondUse() {
		return diamondUse;
	}
	public void setDiamondUse(int diamondUse) {
		this.diamondUse = diamondUse;
	}
	public int getDiamondJoin() {
		return diamondJoin;
	}
	public void setDiamondJoin(int diamondJoin) {
		this.diamondJoin = diamondJoin;
	}
	public int getCoinUse() {
		return coinUse;
	}
	public void setCoinUse(int coinUse) {
		this.coinUse = coinUse;
	}
}

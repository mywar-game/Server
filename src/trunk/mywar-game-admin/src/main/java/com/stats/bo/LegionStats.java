package com.stats.bo;

import java.util.Date;

public class LegionStats {

	private int id;
	private int sysNum;
	private int legionJoin;
	private int legionTotal;
	private int diamondUse;
	private int coinUse;
	private int diamondUseTotal;
	private Date time;
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

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

	public int getLegionJoin() {
		return legionJoin;
	}

	public void setLegionJoin(int legionJoin) {
		this.legionJoin = legionJoin;
	}

	public int getLegionTotal() {
		return legionTotal;
	}

	public void setLegionTotal(int legionTotal) {
		this.legionTotal = legionTotal;
	}

	public int getDiamondUse() {
		return diamondUse;
	}

	public void setDiamondUse(int diamondUse) {
		this.diamondUse = diamondUse;
	}

	public int getCoinUse() {
		return coinUse;
	}

	public void setCoinUse(int coinUse) {
		this.coinUse = coinUse;
	}

	public int getDiamondUseTotal() {
		return diamondUseTotal;
	}

	public void setDiamondUseTotal(int diamondUseTotal) {
		this.diamondUseTotal = diamondUseTotal;
	}

	public LegionStats() {}
	
}

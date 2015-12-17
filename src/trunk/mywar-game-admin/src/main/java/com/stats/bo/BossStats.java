package com.stats.bo;

import java.util.Date;

public class BossStats {

	private Integer id;
	private Integer sysNum;
	private Date time;
	
	private Integer fightCount;
	private Integer diamondGuwuCount;
	private Integer diamondGuwuCiCount;
	private Integer fuhuoCount;
	private Integer fuhuoCiCount;
	
	public BossStats() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSysNum() {
		return sysNum;
	}
	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getFightCount() {
		return fightCount;
	}
	public void setFightCount(Integer fightCount) {
		this.fightCount = fightCount;
	}
	public Integer getDiamondGuwuCount() {
		return diamondGuwuCount;
	}
	public void setDiamondGuwuCount(Integer diamondGuwuCount) {
		this.diamondGuwuCount = diamondGuwuCount;
	}
	public Integer getDiamondGuwuCiCount() {
		return diamondGuwuCiCount;
	}
	public void setDiamondGuwuCiCount(Integer diamondGuwuCiCount) {
		this.diamondGuwuCiCount = diamondGuwuCiCount;
	}
	public Integer getFuhuoCount() {
		return fuhuoCount;
	}
	public void setFuhuoCount(Integer fuhuoCount) {
		this.fuhuoCount = fuhuoCount;
	}
	public Integer getFuhuoCiCount() {
		return fuhuoCiCount;
	}
	public void setFuhuoCiCount(Integer fuhuoCiCount) {
		this.fuhuoCiCount = fuhuoCiCount;
	}
	
}

package com.stats.bo;

import java.util.Date;
/**
 * 争霸
 * @author Administrator
 *
 */

public class ZhengbaStats {

	private Integer id;
	private Integer sysNum;
	private Date time;
	private Integer fightUserCount;
	private Integer fightTimes;
	private Integer endrance;
	
	public ZhengbaStats(){}
	
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
	public Integer getFightUserCount() {
		return fightUserCount;
	}
	public void setFightUserCount(Integer fightUserCount) {
		this.fightUserCount = fightUserCount;
	}
	public Integer getFightTimes() {
		return fightTimes;
	}
	public void setFightTimes(Integer fightTimes) {
		this.fightTimes = fightTimes;
	}
	public Integer getEndrance() {
		return endrance;
	}
	public void setEndrance(Integer endrance) {
		this.endrance = endrance;
	}
}

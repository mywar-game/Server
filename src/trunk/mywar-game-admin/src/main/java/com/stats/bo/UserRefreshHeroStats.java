package com.stats.bo;

import java.util.Date;

/**
 * UserRefreshHeroStats entity. @author MyEclipse Persistence Tools
 */

public class UserRefreshHeroStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Date date;
	private Integer refreshTimes;
	private Integer chargeTimes;
	private Integer commonQualityNum;
	private Integer excellentQualityNum;
	private Integer eliteQualityNum;
	private Integer superQualityNum;
	private Integer recruitHeroNum;

	// Constructors

	/** default constructor */
	public UserRefreshHeroStats() {
	}

	/** full constructor */
	public UserRefreshHeroStats(Integer sysNum, Date date,
			Integer refreshTimes, Integer chargeTimes,
			Integer commonQualityNum, Integer excellentQualityNum,
			Integer eliteQualityNum, Integer superQualityNum,
			Integer recruitHeroNum) {
		this.sysNum = sysNum;
		this.date = date;
		this.refreshTimes = refreshTimes;
		this.chargeTimes = chargeTimes;
		this.commonQualityNum = commonQualityNum;
		this.excellentQualityNum = excellentQualityNum;
		this.eliteQualityNum = eliteQualityNum;
		this.superQualityNum = superQualityNum;
		this.recruitHeroNum = recruitHeroNum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getRefreshTimes() {
		return this.refreshTimes;
	}

	public void setRefreshTimes(Integer refreshTimes) {
		this.refreshTimes = refreshTimes;
	}

	public Integer getChargeTimes() {
		return this.chargeTimes;
	}

	public void setChargeTimes(Integer chargeTimes) {
		this.chargeTimes = chargeTimes;
	}

	public Integer getCommonQualityNum() {
		return this.commonQualityNum;
	}

	public void setCommonQualityNum(Integer commonQualityNum) {
		this.commonQualityNum = commonQualityNum;
	}

	public Integer getExcellentQualityNum() {
		return this.excellentQualityNum;
	}

	public void setExcellentQualityNum(Integer excellentQualityNum) {
		this.excellentQualityNum = excellentQualityNum;
	}

	public Integer getEliteQualityNum() {
		return this.eliteQualityNum;
	}

	public void setEliteQualityNum(Integer eliteQualityNum) {
		this.eliteQualityNum = eliteQualityNum;
	}

	public Integer getSuperQualityNum() {
		return this.superQualityNum;
	}

	public void setSuperQualityNum(Integer superQualityNum) {
		this.superQualityNum = superQualityNum;
	}

	public Integer getRecruitHeroNum() {
		return this.recruitHeroNum;
	}

	public void setRecruitHeroNum(Integer recruitHeroNum) {
		this.recruitHeroNum = recruitHeroNum;
	}

}
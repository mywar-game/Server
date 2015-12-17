package com.stats.bo;

import java.util.Date;

/**
 * UserMallRankStats entity. @author MyEclipse Persistence Tools
 */

public class UserMallRankStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Integer rank;
	private Integer costTreasureId;
	private Integer costNum;
	private Integer buyNumTreasureId;
	private Integer buyNum;
	private Integer buyUserNumTreasureId;
	private Integer buyUserNum;
	private Date time;

	// Constructors

	/** default constructor */
	public UserMallRankStats() {
	}

	/** minimal constructor */
	public UserMallRankStats(Integer sysNum, Integer rank, Date time) {
		this.sysNum = sysNum;
		this.rank = rank;
		this.time = time;
	}

	/** full constructor */
	public UserMallRankStats(Integer sysNum, Integer rank,
			Integer costTreasureId, Integer costNum, Integer buyNumTreasureId,
			Integer buyNum, Integer buyUserNumTreasureId, Integer buyUserNum,
			Date time) {
		this.sysNum = sysNum;
		this.rank = rank;
		this.costTreasureId = costTreasureId;
		this.costNum = costNum;
		this.buyNumTreasureId = buyNumTreasureId;
		this.buyNum = buyNum;
		this.buyUserNumTreasureId = buyUserNumTreasureId;
		this.buyUserNum = buyUserNum;
		this.time = time;
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

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getCostTreasureId() {
		return this.costTreasureId;
	}

	public void setCostTreasureId(Integer costTreasureId) {
		this.costTreasureId = costTreasureId;
	}

	public Integer getCostNum() {
		return this.costNum;
	}

	public void setCostNum(Integer costNum) {
		this.costNum = costNum;
	}

	public Integer getBuyNumTreasureId() {
		return this.buyNumTreasureId;
	}

	public void setBuyNumTreasureId(Integer buyNumTreasureId) {
		this.buyNumTreasureId = buyNumTreasureId;
	}

	public Integer getBuyNum() {
		return this.buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public Integer getBuyUserNumTreasureId() {
		return this.buyUserNumTreasureId;
	}

	public void setBuyUserNumTreasureId(Integer buyUserNumTreasureId) {
		this.buyUserNumTreasureId = buyUserNumTreasureId;
	}

	public Integer getBuyUserNum() {
		return this.buyUserNum;
	}

	public void setBuyUserNum(Integer buyUserNum) {
		this.buyUserNum = buyUserNum;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
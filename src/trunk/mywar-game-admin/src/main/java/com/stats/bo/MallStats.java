package com.stats.bo;

import java.util.Date;

/**
 * MallStats entity. @author MyEclipse Persistence Tools
 */

public class MallStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String channel;
	private Integer treasureId;
	private String treasureName;
	private Integer price;
	private Integer saleNum;
	private Integer costGold;
	private Integer buyUserNum;
	private Date time;

	// Constructors

	/** default constructor */
	public MallStats() {
	}

	/** full constructor */
	public MallStats(Integer sysNum, String channel, Integer treasureId,
			String treasureName, Integer price, Integer saleNum,
			Integer costGold, Integer buyUserNum, Date time) {
		this.sysNum = sysNum;
		this.channel = channel;
		this.treasureId = treasureId;
		this.treasureName = treasureName;
		this.price = price;
		this.saleNum = saleNum;
		this.costGold = costGold;
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

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getTreasureId() {
		return this.treasureId;
	}

	public void setTreasureId(Integer treasureId) {
		this.treasureId = treasureId;
	}

	public String getTreasureName() {
		return this.treasureName;
	}

	public void setTreasureName(String treasureName) {
		this.treasureName = treasureName;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getSaleNum() {
		return this.saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Integer getCostGold() {
		return this.costGold;
	}

	public void setCostGold(Integer costGold) {
		this.costGold = costGold;
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
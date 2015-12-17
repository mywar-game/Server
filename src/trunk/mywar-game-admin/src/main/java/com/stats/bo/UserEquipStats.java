package com.stats.bo;

import java.util.Date;

/**
 * UserEquipStats entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class UserEquipStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Date time;

	// Constructors
	private Integer equipId;
	private String name;
	private Integer quality;
	private Integer equipNumber;

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

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Integer getEquipId() {
		return equipId;
	}

	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEquipNumber() {
		return equipNumber;
	}

	public void setEquipNumber(Integer equipNumber) {
		this.equipNumber = equipNumber;
	}

	/** default constructor */
	public UserEquipStats() {
	}

}
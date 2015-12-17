package com.dataconfig.bo;

/**
 * SystemEquip entity. @author MyEclipse Persistence Tools
 */

public class SystemEquip implements java.io.Serializable {

	// Fields

	private Integer equipId;
	private String equipName;
	private String equipDesc;
	private Integer suitId;
	private Short equipType;
	private Short color;
	private Integer equipStar;
	private Integer imgId;
	private Integer maxLevel;
	private Integer career;

	// Constructors

	/** default constructor */
	public SystemEquip() {
	}

	/** minimal constructor */
	public SystemEquip(String equipName, String equipDesc, Integer suitId,
			Short equipType, Integer equipStar, Integer imgId,
			Integer maxLevel, Integer career) {
		this.equipName = equipName;
		this.equipDesc = equipDesc;
		this.suitId = suitId;
		this.equipType = equipType;
		this.equipStar = equipStar;
		this.imgId = imgId;
		this.maxLevel = maxLevel;
		this.career = career;
	}

	/** full constructor */
	public SystemEquip(String equipName, String equipDesc, Integer suitId,
			Short equipType, Short color, Integer equipStar, Integer imgId,
			Integer maxLevel, Integer career) {
		this.equipName = equipName;
		this.equipDesc = equipDesc;
		this.suitId = suitId;
		this.equipType = equipType;
		this.color = color;
		this.equipStar = equipStar;
		this.imgId = imgId;
		this.maxLevel = maxLevel;
		this.career = career;
	}

	// Property accessors

	public Integer getEquipId() {
		return this.equipId;
	}

	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}

	public String getEquipName() {
		return this.equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public String getEquipDesc() {
		return this.equipDesc;
	}

	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}

	public Integer getSuitId() {
		return this.suitId;
	}

	public void setSuitId(Integer suitId) {
		this.suitId = suitId;
	}

	public Short getEquipType() {
		return this.equipType;
	}

	public void setEquipType(Short equipType) {
		this.equipType = equipType;
	}

	public Short getColor() {
		return this.color;
	}

	public void setColor(Short color) {
		this.color = color;
	}

	public Integer getEquipStar() {
		return this.equipStar;
	}

	public void setEquipStar(Integer equipStar) {
		this.equipStar = equipStar;
	}

	public Integer getImgId() {
		return this.imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getMaxLevel() {
		return this.maxLevel;
	}

	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}

	public Integer getCareer() {
		return this.career;
	}

	public void setCareer(Integer career) {
		this.career = career;
	}

}
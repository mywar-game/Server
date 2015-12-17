package com.dataconfig.bo;

/**
 * BNpcConstant entity. @author MyEclipse Persistence Tools
 */

public class BNpcConstant implements java.io.Serializable {

	// Fields

	private Integer npcId;
	private Integer buildingId;
	private String npcName;
	private String npcDescription;
	private Integer coolTime;

	// Constructors

	/** default constructor */
	public BNpcConstant() {
	}

	/** full constructor */
	public BNpcConstant(Integer buildingId, String npcName,
			String npcDescription, Integer coolTime) {
		this.buildingId = buildingId;
		this.npcName = npcName;
		this.npcDescription = npcDescription;
		this.coolTime = coolTime;
	}
	
	public BNpcConstant(Integer npcId, String npcName) {
		this.npcId = npcId;
		this.npcName = npcName;
	}

	// Property accessors

	public Integer getNpcId() {
		return this.npcId;
	}

	public void setNpcId(Integer npcId) {
		this.npcId = npcId;
	}

	public Integer getBuildingId() {
		return this.buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getNpcName() {
		return this.npcName;
	}

	public void setNpcName(String npcName) {
		this.npcName = npcName;
	}

	public String getNpcDescription() {
		return this.npcDescription;
	}

	public void setNpcDescription(String npcDescription) {
		this.npcDescription = npcDescription;
	}

	public Integer getCoolTime() {
		return this.coolTime;
	}

	public void setCoolTime(Integer coolTime) {
		this.coolTime = coolTime;
	}

}
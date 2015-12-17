package com.dataconfig.bo;

/**
 * MStoryConstant entity. @author MyEclipse Persistence Tools
 */

public class MStoryConstant implements java.io.Serializable {

	// Fields

	private Integer storyId;
	private String storyDesc;
	private Integer type;
	private Integer npcId;
	private String npcName;

	// Constructors

	/** default constructor */
	public MStoryConstant() {
	}

	/** full constructor */
	public MStoryConstant(String storyDesc, Integer type, Integer npcId,
			String npcName) {
		this.storyDesc = storyDesc;
		this.type = type;
		this.npcId = npcId;
		this.npcName = npcName;
	}

	// Property accessors

	public Integer getStoryId() {
		return this.storyId;
	}

	public void setStoryId(Integer storyId) {
		this.storyId = storyId;
	}

	public String getStoryDesc() {
		return this.storyDesc;
	}

	public void setStoryDesc(String storyDesc) {
		this.storyDesc = storyDesc;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getNpcId() {
		return this.npcId;
	}

	public void setNpcId(Integer npcId) {
		this.npcId = npcId;
	}

	public String getNpcName() {
		return this.npcName;
	}

	public void setNpcName(String npcName) {
		this.npcName = npcName;
	}

}
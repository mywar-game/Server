package com.dataconfig.bo;

/**
 * BAttrConstant entity. @author MyEclipse Persistence Tools
 */

public class BAttrConstant implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer attrId;
	private Integer attrLevel;
	private Integer value;

	// Constructors

	/** default constructor */
	public BAttrConstant() {
	}

	/** full constructor */
	public BAttrConstant(Integer attrId, Integer attrLevel, Integer value) {
		this.attrId = attrId;
		this.attrLevel = attrLevel;
		this.value = value;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAttrId() {
		return this.attrId;
	}

	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}

	public Integer getAttrLevel() {
		return this.attrLevel;
	}

	public void setAttrLevel(Integer attrLevel) {
		this.attrLevel = attrLevel;
	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
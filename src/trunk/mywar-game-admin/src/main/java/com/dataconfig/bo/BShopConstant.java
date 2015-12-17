package com.dataconfig.bo;

/**
 * BShopConstant entity. @author MyEclipse Persistence Tools
 */

public class BShopConstant implements java.io.Serializable {

	// Fields

	private Integer shopLevel;
	private Integer outputMax;
	private Integer outputRatio;

	// Constructors

	/** default constructor */
	public BShopConstant() {
	}

	/** full constructor */
	public BShopConstant(Integer outputMax, Integer outputRatio) {
		this.outputMax = outputMax;
		this.outputRatio = outputRatio;
	}

	// Property accessors

	public Integer getShopLevel() {
		return this.shopLevel;
	}

	public void setShopLevel(Integer shopLevel) {
		this.shopLevel = shopLevel;
	}

	public Integer getOutputMax() {
		return this.outputMax;
	}

	public void setOutputMax(Integer outputMax) {
		this.outputMax = outputMax;
	}

	public Integer getOutputRatio() {
		return this.outputRatio;
	}

	public void setOutputRatio(Integer outputRatio) {
		this.outputRatio = outputRatio;
	}

}
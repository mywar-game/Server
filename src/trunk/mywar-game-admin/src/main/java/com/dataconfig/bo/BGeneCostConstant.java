package com.dataconfig.bo;

/**
 * BGeneCostConstant entity. @author MyEclipse Persistence Tools
 */

public class BGeneCostConstant implements java.io.Serializable {

	// Fields

	private Integer geneTotalLevel;
	private Integer genePointCost;
	private Integer goldenCost;
	private String normalRate;
	private String highRate;

	// Constructors

	/** default constructor */
	public BGeneCostConstant() {
	}

	/** full constructor */
	public BGeneCostConstant(Integer genePointCost, Integer goldenCost,
			String normalRate, String highRate) {
		this.genePointCost = genePointCost;
		this.goldenCost = goldenCost;
		this.normalRate = normalRate;
		this.highRate = highRate;
	}

	// Property accessors

	public Integer getGeneTotalLevel() {
		return this.geneTotalLevel;
	}

	public void setGeneTotalLevel(Integer geneTotalLevel) {
		this.geneTotalLevel = geneTotalLevel;
	}

	public Integer getGenePointCost() {
		return this.genePointCost;
	}

	public void setGenePointCost(Integer genePointCost) {
		this.genePointCost = genePointCost;
	}

	public Integer getGoldenCost() {
		return this.goldenCost;
	}

	public void setGoldenCost(Integer goldenCost) {
		this.goldenCost = goldenCost;
	}

	public String getNormalRate() {
		return this.normalRate;
	}

	public void setNormalRate(String normalRate) {
		this.normalRate = normalRate;
	}

	public String getHighRate() {
		return this.highRate;
	}

	public void setHighRate(String highRate) {
		this.highRate = highRate;
	}

}
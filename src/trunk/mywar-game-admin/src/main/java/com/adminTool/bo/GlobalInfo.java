package com.adminTool.bo;

/**
 * GlobalInfo entity. @author MyEclipse Persistence Tools
 */

public class GlobalInfo implements java.io.Serializable {

	// Fields

	private Integer globalInfoId;
	private Integer level;
	private Integer population;
	private Integer activePopulation;
	private Integer payPopulation;
	private Integer sysNum;

	// Constructors

	/** default constructor */
	public GlobalInfo() {
	}
	
	public GlobalInfo(int level) {
		this.level = level;
		this.population = 0;
		this.activePopulation = 0;
		this.payPopulation = 0;
		this.sysNum = 0;
	}


	/** full constructor */
	public GlobalInfo(Integer level, Integer population,
			Integer activePopulation, Integer payPopulation, Integer sysNum) {
		this.level = level;
		this.population = population;
		this.activePopulation = activePopulation;
		this.payPopulation = payPopulation;
		this.sysNum = sysNum;
	}

	// Property accessors

	public Integer getGlobalInfoId() {
		return this.globalInfoId;
	}

	public void setGlobalInfoId(Integer globalInfoId) {
		this.globalInfoId = globalInfoId;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getPopulation() {
		return this.population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public Integer getActivePopulation() {
		return this.activePopulation;
	}

	public void setActivePopulation(Integer activePopulation) {
		this.activePopulation = activePopulation;
	}

	public Integer getPayPopulation() {
		return this.payPopulation;
	}

	public void setPayPopulation(Integer payPopulation) {
		this.payPopulation = payPopulation;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

}
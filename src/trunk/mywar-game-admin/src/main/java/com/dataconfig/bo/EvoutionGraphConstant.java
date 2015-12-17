package com.dataconfig.bo;

/**
 * PEvoutionGraphConstant entity. @author MyEclipse Persistence Tools
 */

public class EvoutionGraphConstant implements java.io.Serializable {

	// Fields

	private Integer petEvolutionGraphId;
	private Integer petIdEvolutionBefore;
	private Integer petIdEvolutionAfter;
	private Integer sucessPercent;

	// Constructors

	/** default constructor */
	public EvoutionGraphConstant() {
	}

	/** full constructor */
	public EvoutionGraphConstant(Integer petIdEvolutionBefore,
			Integer petIdEvolutionAfter, Integer sucessPercent) {
		this.petIdEvolutionBefore = petIdEvolutionBefore;
		this.petIdEvolutionAfter = petIdEvolutionAfter;
		this.sucessPercent = sucessPercent;
	}

	// Property accessors

	public Integer getPetEvolutionGraphId() {
		return this.petEvolutionGraphId;
	}

	public void setPetEvolutionGraphId(Integer petEvolutionGraphId) {
		this.petEvolutionGraphId = petEvolutionGraphId;
	}

	public Integer getPetIdEvolutionBefore() {
		return this.petIdEvolutionBefore;
	}

	public void setPetIdEvolutionBefore(Integer petIdEvolutionBefore) {
		this.petIdEvolutionBefore = petIdEvolutionBefore;
	}

	public Integer getPetIdEvolutionAfter() {
		return this.petIdEvolutionAfter;
	}

	public void setPetIdEvolutionAfter(Integer petIdEvolutionAfter) {
		this.petIdEvolutionAfter = petIdEvolutionAfter;
	}

	public Integer getSucessPercent() {
		return this.sucessPercent;
	}

	public void setSucessPercent(Integer sucessPercent) {
		this.sucessPercent = sucessPercent;
	}

}
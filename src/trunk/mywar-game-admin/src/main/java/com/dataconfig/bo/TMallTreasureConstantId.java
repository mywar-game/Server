package com.dataconfig.bo;

/**
 * TMallTreasureConstantId entity. @author MyEclipse Persistence Tools
 */

public class TMallTreasureConstantId implements java.io.Serializable {

	// Fields

	private Integer treasureId;
	private Integer category;

	// Constructors

	/** default constructor */
	public TMallTreasureConstantId() {
	}

	/** full constructor */
	public TMallTreasureConstantId(Integer treasureId, Integer category) {
		this.treasureId = treasureId;
		this.category = category;
	}

	// Property accessors

	public Integer getTreasureId() {
		return this.treasureId;
	}

	public void setTreasureId(Integer treasureId) {
		this.treasureId = treasureId;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TMallTreasureConstantId))
			return false;
		TMallTreasureConstantId castOther = (TMallTreasureConstantId) other;

		return ((this.getTreasureId() == castOther.getTreasureId()) || (this
				.getTreasureId() != null
				&& castOther.getTreasureId() != null && this.getTreasureId()
				.equals(castOther.getTreasureId())))
				&& ((this.getCategory() == castOther.getCategory()) || (this
						.getCategory() != null
						&& castOther.getCategory() != null && this
						.getCategory().equals(castOther.getCategory())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getTreasureId() == null ? 0 : this.getTreasureId()
						.hashCode());
		result = 37 * result
				+ (getCategory() == null ? 0 : this.getCategory().hashCode());
		return result;
	}

}
package com.dataconfig.bo;

/**
 * BaPveConstantId entity. @author MyEclipse Persistence Tools
 */

public class BaPveConstantId implements java.io.Serializable {

	// Fields

	private Integer pveBigId;
	private Integer pveSmallId;

	// Constructors

	/** default constructor */
	public BaPveConstantId() {
	}

	/** full constructor */
	public BaPveConstantId(Integer pveBigId, Integer pveSmallId) {
		this.pveBigId = pveBigId;
		this.pveSmallId = pveSmallId;
	}

	// Property accessors

	public Integer getPveBigId() {
		return this.pveBigId;
	}

	public void setPveBigId(Integer pveBigId) {
		this.pveBigId = pveBigId;
	}

	public Integer getPveSmallId() {
		return this.pveSmallId;
	}

	public void setPveSmallId(Integer pveSmallId) {
		this.pveSmallId = pveSmallId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BaPveConstantId))
			return false;
		BaPveConstantId castOther = (BaPveConstantId) other;

		return ((this.getPveBigId() == castOther.getPveBigId()) || (this
				.getPveBigId() != null
				&& castOther.getPveBigId() != null && this.getPveBigId()
				.equals(castOther.getPveBigId())))
				&& ((this.getPveSmallId() == castOther.getPveSmallId()) || (this
						.getPveSmallId() != null
						&& castOther.getPveSmallId() != null && this
						.getPveSmallId().equals(castOther.getPveSmallId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPveBigId() == null ? 0 : this.getPveBigId().hashCode());
		result = 37
				* result
				+ (getPveSmallId() == null ? 0 : this.getPveSmallId()
						.hashCode());
		return result;
	}

}
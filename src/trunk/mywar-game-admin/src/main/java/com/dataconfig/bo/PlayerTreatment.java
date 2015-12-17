package com.dataconfig.bo;

import java.sql.Timestamp;

/**
 * PlayerTreatment entity. @author MyEclipse Persistence Tools
 */

public class PlayerTreatment implements java.io.Serializable {

	// Fields

	private Integer playerTreatmentId;
	private Long userId;
	private Integer type;
	private Timestamp opertationFinishTime;

	// Constructors

	/** default constructor */
	public PlayerTreatment() {
	}

	/** full constructor */
	public PlayerTreatment(Long userId, Integer type,
			Timestamp opertationFinishTime) {
		this.userId = userId;
		this.type = type;
		this.opertationFinishTime = opertationFinishTime;
	}

	// Property accessors

	public Integer getPlayerTreatmentId() {
		return this.playerTreatmentId;
	}

	public void setPlayerTreatmentId(Integer playerTreatmentId) {
		this.playerTreatmentId = playerTreatmentId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getOpertationFinishTime() {
		return this.opertationFinishTime;
	}

	public void setOpertationFinishTime(Timestamp opertationFinishTime) {
		this.opertationFinishTime = opertationFinishTime;
	}

}
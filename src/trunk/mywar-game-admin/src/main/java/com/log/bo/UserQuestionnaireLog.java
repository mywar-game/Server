package com.log.bo;

import java.sql.Timestamp;

/**
 * UserQuestionnaireLog entity. @author MyEclipse Persistence Tools
 */

public class UserQuestionnaireLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Long userId;
	private Integer questionId;
	private Integer choice;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public UserQuestionnaireLog() {
	}

	/** full constructor */
	public UserQuestionnaireLog(Long userId, Integer questionId,
			Integer choice, Timestamp time) {
		this.userId = userId;
		this.questionId = questionId;
		this.choice = choice;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getChoice() {
		return this.choice;
	}

	public void setChoice(Integer choice) {
		this.choice = choice;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}
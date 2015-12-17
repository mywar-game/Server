package com.stats.bo;

import java.util.Date;

/**
 * UserQuestionnaireStats entity. @author MyEclipse Persistence Tools
 */

public class UserQuestionnaireStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Date date;
	private Integer questionId;
	private String choice;

	// Constructors

	/** default constructor */
	public UserQuestionnaireStats() {
	}

	/** minimal constructor */
	public UserQuestionnaireStats(Integer sysNum, Date date, String choice) {
		this.sysNum = sysNum;
		this.date = date;
		this.choice = choice;
	}

	/** full constructor */
	public UserQuestionnaireStats(Integer sysNum, Date date,
			Integer questionId, String choice) {
		this.sysNum = sysNum;
		this.date = date;
		this.questionId = questionId;
		this.choice = choice;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getChoice() {
		return this.choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

}
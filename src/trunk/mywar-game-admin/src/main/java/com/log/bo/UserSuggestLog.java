package com.log.bo;

import java.sql.Timestamp;

/**
 * UserSuggestLog entity. @author MyEclipse Persistence Tools
 */

public class UserSuggestLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Long userId;
	private Integer suggestType;
	private String content;
	private Timestamp commitTime;

	// Constructors

	/** default constructor */
	public UserSuggestLog() {
	}

	/** full constructor */
	public UserSuggestLog(Long userId, Integer suggestType, String content,
			Timestamp commitTime) {
		this.userId = userId;
		this.suggestType = suggestType;
		this.content = content;
		this.commitTime = commitTime;
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

	public Integer getSuggestType() {
		return this.suggestType;
	}

	public void setSuggestType(Integer suggestType) {
		this.suggestType = suggestType;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCommitTime() {
		return this.commitTime;
	}

	public void setCommitTime(Timestamp commitTime) {
		this.commitTime = commitTime;
	}

}
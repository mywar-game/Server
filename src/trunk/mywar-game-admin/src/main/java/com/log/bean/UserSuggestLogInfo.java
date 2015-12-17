package com.log.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserSuggestLogInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Long userId;
	private Integer suggestType;
	private String name;
	private String content;
	private Timestamp commitTime;
	private String adminName;
	private Integer dealType;
	private Timestamp dealTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getSuggestType() {
		return suggestType;
	}
	public void setSuggestType(Integer suggestType) {
		this.suggestType = suggestType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCommitTime(Timestamp commitTime) {
		this.commitTime = commitTime;
	}
	public Timestamp getCommitTime() {
		return commitTime;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}
	public Integer getDealType() {
		return dealType;
	}
	public void setDealTime(Timestamp dealTime) {
		this.dealTime = dealTime;
	}
	public Timestamp getDealTime() {
		return dealTime;
	}

	
	

}

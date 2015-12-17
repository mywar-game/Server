package com.adminTool.bo;

import java.sql.Timestamp;

/**
 * UserRankSnapshot entity. @author MyEclipse Persistence Tools
 */

public class UserRankSnapshot implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String createName;
	private Integer type;
	private Long userId;
	private String name;
	private Integer rank;
	private String note;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserRankSnapshot() {
	}

	/** full constructor */
	public UserRankSnapshot(Integer sysNum, String createName, Integer type,
			Long userId, String name, Integer rank, String note,
			Timestamp createTime) {
		this.sysNum = sysNum;
		this.createName = createName;
		this.type = type;
		this.userId = userId;
		this.name = name;
		this.rank = rank;
		this.note = note;
		this.createTime = createTime;
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

	public String getCreateName() {
		return this.createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
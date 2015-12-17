package com.stats.bo;

import java.util.Date;

/**
 * 作弊记录
 * @author Administrator
 *
 */
public class CheatStats {

	public CheatStats() {}
	
	private int id;
	private int sysNum;
	private Date createTime;
	private String userId;
	private int operation;
	private int treasureId;
	private int treasureType;
	private Date date;
	private int times; // 次数
	
	private String toolDesc;
	
	public String getToolDesc() {
		return toolDesc;
	}
	public void setToolDesc(String toolDesc) {
		this.toolDesc = toolDesc;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSysNum() {
		return sysNum;
	}
	public void setSysNum(int sysNum) {
		this.sysNum = sysNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getOperation() {
		return operation;
	}
	public void setOperation(int operation) {
		this.operation = operation;
	}
	public int getTreasureId() {
		return treasureId;
	}
	public void setTreasureId(int treasureId) {
		this.treasureId = treasureId;
	}
	public int getTreasureType() {
		return treasureType;
	}
	public void setTreasureType(int treasureType) {
		this.treasureType = treasureType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}

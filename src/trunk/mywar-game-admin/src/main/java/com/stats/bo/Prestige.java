package com.stats.bo;

import java.util.Date;

/**
 * 声望
 * @author Administrator
 *
 */
public class Prestige {

	private Integer id;
	private Integer sysNum;
	private Date time;
	private String toolName;
	private String userCount;
	private String toolCount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSysNum() {
		return sysNum;
	}
	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public String getUserCount() {
		return userCount;
	}
	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}
	public String getToolCount() {
		return toolCount;
	}
	public void setToolCount(String toolCount) {
		this.toolCount = toolCount;
	}
	public Prestige() {
		
	}
}

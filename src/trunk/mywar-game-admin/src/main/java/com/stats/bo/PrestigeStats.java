package com.stats.bo;

import java.util.Date;

/**
 * 声望
 * @author Administrator
 *
 */
public class PrestigeStats {

	private Integer id;
	private Integer sysNum;
	private Date time;
	private String toolDesc;
	private String userDesc;
	
	/** 不做映射处理 前端显示**/
	private String toolName;
	private String userCount;
	private String toolCount;
	
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

	public PrestigeStats() {
		
	}
	
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

	public String getToolDesc() {
		return toolDesc;
	}

	public void setToolDesc(String toolDesc) {
		this.toolDesc = toolDesc;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

}

package com.adminTool.msgbody;

import java.util.Date;

public class UserArtifactSomeInfo {

	/**
	 * 表id
	 */
	private String userArtifactId;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 佩戴神器的用户武将ID
	 */
	private String userHeroId="";

	/**
	 * 神器ID
	 */
	private int artifactId;

	/**
	 * 神器属性json字符串    对象为 ： Artifact
	 */
	private String artifactAttr;

	/**
	 * 创建时间
	 */
	private Date createdTime;

	/**
	 * 更新时间
	 */
	private Date updatedTime;
	
	/**
	 * 神器等级
	 */
	private int artifactLevel;


	public int getArtifactLevel() {
		return artifactLevel;
	}

	public void setArtifactLevel(int artifactLevel) {
		this.artifactLevel = artifactLevel;
	}

	public String getUserArtifactId() {
		return userArtifactId;
	}

	public void setUserArtifactId(String userArtifactId) {
		this.userArtifactId = userArtifactId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserHeroId() {
		return userHeroId;
	}

	public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}

	public int getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(int artifactId) {
		this.artifactId = artifactId;
	}

	public String getArtifactAttr() {
		return artifactAttr;
	}

	public void setArtifactAttr(String artifactAttr) {
		this.artifactAttr = artifactAttr;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}

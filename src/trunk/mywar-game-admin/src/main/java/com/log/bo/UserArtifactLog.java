package com.log.bo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户神器操作日志
 * 
 * @author yezp
 */
public class UserArtifactLog implements Serializable {

	/**
	 * 神器操作日志id
	 */
	private Integer userArtifactLogId;

	/**
	 * 用户编号
	 */
	private String userId;

	/**
	 * 用户神器id
	 */
	private String userArtifactId;

	/**
	 * 神器id
	 */
	private Integer artifactId;

	/**
	 * 当前属性值
	 */
	private String artifactAttStr;

	/**
	 * 数量
	 */
	private Integer num;

	/**
	 * 操作类型(1 获取 2普通锻造 3保存普通 4放弃普通锻造 5元宝锻造 6进阶 7被进阶 8出售)
	 */
	private Integer operationType;

	/**
	 * 操作子类型（与tool的userType相同）
	 */
	private Integer operationSmallType;

	/**
	 * 操作时间
	 */
	private Timestamp createdTime;

	public UserArtifactLog() {
	}

	public Integer getUserArtifactLogId() {
		return userArtifactLogId;
	}

	public void setUserArtifactLogId(Integer userArtifactLogId) {
		this.userArtifactLogId = userArtifactLogId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserArtifactId() {
		return userArtifactId;
	}

	public void setUserArtifactId(String userArtifactId) {
		this.userArtifactId = userArtifactId;
	}

	public Integer getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(Integer artifactId) {
		this.artifactId = artifactId;
	}

	public String getArtifactAttStr() {
		return artifactAttStr;
	}

	public void setArtifactAttStr(String artifactAttStr) {
		this.artifactAttStr = artifactAttStr;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	public Integer getOperationSmallType() {
		return operationSmallType;
	}

	public void setOperationSmallType(Integer operationSmallType) {
		this.operationSmallType = operationSmallType;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

}

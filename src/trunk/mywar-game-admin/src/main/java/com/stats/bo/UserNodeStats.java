package com.stats.bo;

/**
 * 用户节点统计
 * 
 * @author yezp
 */
public class UserNodeStats implements java.io.Serializable {

	private static final long serialVersionUID = -2445704358896125383L;

	private Integer actionId;
	private String actionDesc;
	private Integer actionType;
	private int times;
	private int userNum;

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public String getActionDesc() {
		return actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

}

package com.log.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * UserEquipmentLog entity. @author MyEclipse Persistence Tools
 */

public class UserEquipmentLog implements java.io.Serializable {

	// Fields

	private Integer userEquipmentLogId;
	private String userId;
	private String userEquipmentId;
	private Integer equipmentId;
	private String userHeroId;
	private Integer equipmentLevel;
	private Integer type;
	private Timestamp operationTime;
	/**玩家信息**/
	private String userName;
	private int lodoId;

	// Constructors

	/** default constructor */
	public UserEquipmentLog() {
	}

	/** full constructor */
	public UserEquipmentLog(Integer userEquipmentLogId,String userId, String userEquipmentId,
			Integer equipmentId, String userHeroId, Integer equipmentLevel,
			Integer type, Date operationTime,String userName,int lodoId) {
		this.userEquipmentLogId = userEquipmentLogId;
		this.userId = userId;
		this.userEquipmentId = userEquipmentId;
		this.equipmentId = equipmentId;
		this.userHeroId = userHeroId;
		this.equipmentLevel = equipmentLevel;
		this.type = type;
		this.operationTime = new Timestamp(operationTime.getTime());
		this.userName = userName;
		this.lodoId = lodoId;
	}

	// Property accessors

	public Integer getUserEquipmentLogId() {
		return this.userEquipmentLogId;
	}

	public void setUserEquipmentLogId(Integer userEquipmentLogId) {
		this.userEquipmentLogId = userEquipmentLogId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserEquipmentId() {
		return this.userEquipmentId;
	}

	public void setUserEquipmentId(String userEquipmentId) {
		this.userEquipmentId = userEquipmentId;
	}

	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getLodoId() {
		return lodoId;
	}

	public void setLodoId(int lodoId) {
		this.lodoId = lodoId;
	}

	public String getUserHeroId() {
		return this.userHeroId;
	}

	public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}

	public Integer getEquipmentLevel() {
		return this.equipmentLevel;
	}

	public void setEquipmentLevel(Integer equipmentLevel) {
		this.equipmentLevel = equipmentLevel;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(Timestamp operationTime) {
		this.operationTime = operationTime;
	}

}
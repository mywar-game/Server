package com.adminTool.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.admin.util.Tools;
import com.adminTool.msgbody.UserEquipmentSomeInfo;
import com.framework.common.ALDAdminActionSupport;
import com.framework.log.LogSystem;

/**
 * 修改玩家装备附带技能
 * @author hzy
 * 2012-11-1
 */
public class ModifyUserEquipmentSkill extends ALDAdminActionSupport {

	/** * */
	private static final long serialVersionUID = -2650821157768374083L;
	
	/**  玩家装备id **/
	private String userEquipmentID;
	
	/**  玩家ID **/
	private long userId;
	
	/**
	 * 搜索的玩家角色名
	 */
	private String searchName;
	
	/** 技能id字符串 */
	private String skillPoints;
	
	/** 技能名 */
	private String skillNames;
	
	/** 装备名 */
	private String equipmentName;
	
	private String isCommit = "F";

	public String execute() {
		if ("F".equals(isCommit)) {
			if (Tools.isEmpty(equipmentName)) {
				super.setErroDescrip("装备名为空！");
				return SUCCESS;
			}
			try {
				equipmentName = URLDecoder.decode(equipmentName, "UTF-8").trim();
				if (!Tools.isEmpty(skillNames)) {
					skillNames = URLDecoder.decode(skillNames, "UTF-8").trim();
				}
			} catch (UnsupportedEncodingException e) {
				LogSystem.error(e, "");
			}
			return INPUT;
		}
		
		UserEquipmentSomeInfo userEquipmentSomeInfo = new UserEquipmentSomeInfo();
		userEquipmentSomeInfo.setUserEquipId(userEquipmentID);
		
//		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.MODIFY_USER_EQUIPMENT_SKILL, userEquipmentSomeInfo, CommomMsgBody.class);
//		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
//			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
//			super.setErroCodeNum(commomMsgBody.getErrorCode());
//			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 修改玩家装备附带技能失败！");
//			return INPUT;
//		}
		
		super.setErroDescrip("修改玩家装备附带技能成功！");
//		System.out.println("--------------------"+skillPoints);
		return SUCCESS;
		
	}

	public void setUserEquipmentID(String userEquipmentID) {
		this.userEquipmentID = userEquipmentID;
	}

	public String getUserEquipmentID() {
		return userEquipmentID;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	/**
	 * @return the skillPoints
	 */
	public String getSkillPoints() {
		return skillPoints;
	}

	/**
	 * @param skillPoints the skillPoints to set
	 */
	public void setSkillPoints(String skillPoints) {
		this.skillPoints = skillPoints;
	}

	public void setSkillNames(String skillNames) {
		this.skillNames = skillNames;
	}

	public String getSkillNames() {
		return skillNames;
	}

	/**
	 * 获取 搜索的玩家角色名 
	 */
	public String getSearchName() {
		return searchName;
	}

	/**
	 * 设置 搜索的玩家角色名 
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
}

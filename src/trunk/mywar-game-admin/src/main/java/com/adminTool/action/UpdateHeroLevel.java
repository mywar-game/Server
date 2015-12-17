package com.adminTool.action;

import com.adminTool.constant.AdminToolCMDCode;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.common.SystemCode;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.hero.msgbody.UpdateHeroLevelMsg;

public class UpdateHeroLevel extends ALDAdminActionSupport{
	
	/**  英雄ID **/
	private String userHeroId;
	/**  英雄ID **/
	private String userId;
	/**  修改的玩家等级 **/
	private int heroLevel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String execute() {
		if(userHeroId == null || userId == null){
			return SUCCESS;
		}
		UpdateHeroLevelMsg updateHeroLevelMsg = new UpdateHeroLevelMsg(); 
		updateHeroLevelMsg.setUserHeroId(userHeroId);
		updateHeroLevelMsg.setUserId(userId);
		updateHeroLevelMsg.setLevel(heroLevel);

		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.UPDATE_USER_LEVEL, updateHeroLevelMsg, CommomMsgBody.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 修改玩家英雄等级！");
			return SUCCESS;
		}
		super.setErroCodeNum(SystemCode.SYS_SUCESS);
		super.setErroDescrip("修改玩家英雄等级！");
		return SUCCESS;
	}

	public String getUserHeroId() {
		return userHeroId;
	}

	public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getHeroLevel() {
		return heroLevel;
	}

	public void setHeroLevel(int heroLevel) {
		this.heroLevel = heroLevel;
	}

}

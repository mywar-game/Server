package com.log.action;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.UserHeroSomeInfo;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.common.SystemCode;
import com.framework.constant.SystemConstant;
import com.framework.log.LogSystem;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserHeroLog;
import com.log.msgbody.ReqGetHeroBack;
import com.log.service.UserHeroLogService;

/**
 * 找回玩家英雄
 * @author hzy
 * 2012-7-30
 */
public class GetHeroBack extends ALDAdminActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long userHeroLogId;
	
	@Override
	public String execute() {
		if (userHeroLogId == null) {
			super.setErroCodeNum(SystemCode.SYS_FAIL);
			super.setErroDescrip("userHeroLogId为空，找回玩家英雄失败！");
			return ERROR;
		}
		UserHeroLogService userHeroLogService = ServiceCacheFactory.getServiceCache().getService(UserHeroLogService.class);
		UserHeroLog userHeroLog = userHeroLogService.findOneUserHeroLog(userHeroLogId);
		UserHeroSomeInfo userHeroSomeInfo = new UserHeroSomeInfo();
		try {
			BeanUtils.copyProperties(userHeroSomeInfo, userHeroLog);
		} catch (IllegalAccessException e) {
			LogSystem.error(e, "");
		} catch (InvocationTargetException e) {
			LogSystem.error(e, "");
		}
//		userHeroSomeInfo.setHeroID(userHeroLog.getHeroId());
//		userHeroSomeInfo.setName(userHeroLog.getHeroName());
		ReqGetHeroBack req = new ReqGetHeroBack();
		req.setUserHeroSomeInfo(userHeroSomeInfo);
		
		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.GET_HERO_BACK, req, CommomMsgBody.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 找回玩家英雄失败！");
			return ERROR;
		}
		super.setErroDescrip("找回玩家英雄成功！");
		super.setErroCodeNum(SystemCode.SYS_SUCESS);
		userHeroLogService.delOneUserHeroLog(userHeroLogId);
		return SUCCESS;
	}
	

	/**
	 * @return the userHeroLogId
	 */
	public Long getUserHeroLogId() {
		return userHeroLogId;
	}

	/**
	 * @param userHeroLogId the userHeroLogId to set
	 */
	public void setUserHeroLogId(Long userHeroLogId) {
		this.userHeroLogId = userHeroLogId;
	}
}

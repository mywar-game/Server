package com.log.action;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.UserEquipmentSomeInfo;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.common.SystemCode;
import com.framework.constant.SystemConstant;
import com.framework.log.LogSystem;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserEquipmentLog;
import com.log.msgbody.ReqGetEquipmentBack;
import com.log.service.UserEquipmentLogService;

/**
 * 找回玩家装备
 * @author hzy
 * 2012-7-30
 */
public class GetEquipmentBack extends ALDAdminActionSupport {

	/**  */
	private static final long serialVersionUID = -2127895703003643846L;
	
	/**  */
	private Integer userEquipmentLogId;
	
	@Override
	public String execute() throws Exception {
		if (userEquipmentLogId == null) {
			super.setErroCodeNum(SystemCode.SYS_FAIL);
			super.setErroDescrip("userEquipmentLogId为空，找回玩家装备失败！");
			return ERROR;
		}
		UserEquipmentLogService userEquipmentLogService = ServiceCacheFactory.getServiceCache().getService(UserEquipmentLogService.class);
		UserEquipmentLog userEquipmentLog =  userEquipmentLogService.findOneUserEquipmentLog(userEquipmentLogId);
		UserEquipmentSomeInfo userEquipmentSomeInfo = new UserEquipmentSomeInfo();
		try {
			BeanUtils.copyProperties(userEquipmentSomeInfo, userEquipmentLog);
		} catch (IllegalAccessException e) {
			LogSystem.error(e, "");
		} catch (InvocationTargetException e) {
			LogSystem.error(e, "");
		}

		ReqGetEquipmentBack req = new ReqGetEquipmentBack();
		userEquipmentSomeInfo.setEquipId(userEquipmentLog.getEquipmentId());
		req.setUserEquipmentSomeInfo(userEquipmentSomeInfo);

		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.GET_EQUIPMENT_BACK, req, CommomMsgBody.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 找回玩家装备失败！");
			return ERROR;
		}
		super.setErroCodeNum(SystemCode.SYS_SUCESS);
		super.setErroDescrip("找回玩家装备成功！");
		userEquipmentLogService.delOneUserEquipmentLog(userEquipmentLogId);
		return SUCCESS;
	}

	/**
	 * @return the userEquipmentLogId
	 */
	public Integer getUserEquipmentLogId() {
		return userEquipmentLogId;
	}

	/**
	 * @param userEquipmentLogId the userEquipmentLogId to set
	 */
	public void setUserEquipmentLogId(Integer userEquipmentLogId) {
		this.userEquipmentLogId = userEquipmentLogId;
	}
}

package com.adminTool.action;

import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.MemCacheInfo;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;

@SuppressWarnings("serial")
/**
 * 向服务端发送操作信息
 * @param userId
 */
public class MemCacheInfoList extends ALDAdminPageActionSupport {
	
	private MemCacheInfo memCacheInfo;

	public String execute() {
		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.MEM_CACHE_INFO, new CommomMsgBody(), MemCacheInfo.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 查看MemCache状态失败！");
			return SUCCESS;
		}
		memCacheInfo = (MemCacheInfo)msg.getMsgBody();
		
		return SUCCESS;
	}

	public MemCacheInfo getMemCacheInfo() {
		return memCacheInfo;
	}

	public void setMemCacheInfo(MemCacheInfo memCacheInfo) {
		this.memCacheInfo = memCacheInfo;
	}
	

}

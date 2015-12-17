package com.dataconfig.action; 

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.adminTool.constant.AdminToolCMDCode;
import com.dataconfig.msgbody.ResReflashCache;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;

/**
 * 刷新常量表缓存
 * @author hzy
 * 2012-7-26
 */
public class ReflashConstantCache extends ALDAdminActionSupport {

	/**  */
	private static final long serialVersionUID = -77728046254373827L; 
	
	@Override
	public String execute() {
		
		HttpServletRequest request = ServletActionContext.getRequest(); 
		
		int cacheType = request.getParameter("cacheType") == null ? 0 : Integer.parseInt(request.getParameter("cacheType").toString()); 

		ResReflashCache resReflashCache = new ResReflashCache(); 
		resReflashCache.setCacheType(cacheType); 
		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.REFLASH_CACHE, resReflashCache, CommomMsgBody.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription());
			return ERROR;
		}
		return SUCCESS; 
	}

}

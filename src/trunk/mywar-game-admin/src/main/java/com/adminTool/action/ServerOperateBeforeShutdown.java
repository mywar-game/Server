package com.adminTool.action;

import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ReqServerOperateBeforeShutdown;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.common.SystemCode;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;

public class ServerOperateBeforeShutdown extends ALDAdminActionSupport {

	private static final long serialVersionUID = 1L;
	
	private int operateNum = -1;
	public String execute(){
		if (operateNum == -1) {
			return SUCCESS; 
		}
		if(operateNum!=1 && operateNum!=2){
			super.setErroCodeNum(SystemCode.SYS_FAIL); 
			super.setErroDescrip("无效操作"); 
			return SUCCESS; 
		}
		ReqServerOperateBeforeShutdown reqServerOperateBeforeShutdown = new ReqServerOperateBeforeShutdown();
		reqServerOperateBeforeShutdown.setOperateNum(operateNum);
		
		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.SERVER_OPERATE_BEFORE_SHUTDOWN, reqServerOperateBeforeShutdown, CommomMsgBody.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 操作失败！");
			return SUCCESS;
		}
		super.setErroCodeNum(SystemConstant.SUCCESS_CODE); 
		if(operateNum==1){
			super.setErroDescrip("成功踢出在线用户!"); 
		}else{
			super.setErroDescrip("成功同步用户数据!"); 
		}
		return SUCCESS; 
	}
	
	public int getOperateNum() {
		return operateNum;
	}
	
	public void setOperateNum(int operateNum) {
		this.operateNum = operateNum;
	}
	
}

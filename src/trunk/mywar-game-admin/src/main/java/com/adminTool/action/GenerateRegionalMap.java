package com.adminTool.action; 

import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ReqGenerateRegionalMap;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.common.SystemCode;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;

public class GenerateRegionalMap extends ALDAdminActionSupport {
	
	/** * */
	private static final long serialVersionUID = 1L;

	private int generateNum; 
	
	private String isCommit = "F";
	
	public String execute() {
		if ("T".equals(this.getIsCommit())) {
			super.setErroCodeNum(SystemConstant.SUCCESS_CODE); 
			super.setErroDescrip(this.getText("generateRegionalMapJsp.success")); 
			if (generateNum <= 0) {
				super.setErroCodeNum(SystemCode.SYS_FAIL); 
				super.setErroDescrip(this.getText("generateRegionalMapJsp.num")); 
				return SUCCESS; 
			}
			ReqGenerateRegionalMap reqGenerateRegionalMap = new ReqGenerateRegionalMap(); 
			int num = (int) Math.ceil((float) generateNum / 20); 
			reqGenerateRegionalMap.setStartPage(0); 
			reqGenerateRegionalMap.setEndPage(num); 
			
			Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.GENERATE_REGIONAL_MAP, reqGenerateRegionalMap, CommomMsgBody.class);
			if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
				CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
				super.setErroCodeNum(commomMsgBody.getErrorCode());
				super.setErroDescrip(commomMsgBody.getErrorDescription()+" 生成区域地图失败！");
			}
		}
		return SUCCESS; 
	}
	
	public String getIsCommit() {
		return isCommit; 
	}
	
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit; 
	}
	
	public int getGenerateNum() {
		return generateNum; 
	}
	
	public void setGenerateNum(int generateNum) {
		this.generateNum = generateNum; 
	}
	
}

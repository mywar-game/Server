package com.adminTool.action;

import net.sf.json.JSONObject;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;

public class DelUserBagSingle extends ALDAdminActionSupport{

	private static final long serialVersionUID = 6714681883336559490L;
	private static final String REQ_URL = "delUserTool.do";
	private String userId;
	private String toolType;
	private String toolId;
	private String toolNum;
	private String flag = "del";
	
	public String execute() { 
		
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		sb.append("&userId=").append(userId);
		sb.append("&toolType=").append(toolType);
		sb.append("&toolId=").append(toolId);
		sb.append("&toolNum=").append(toolNum);
		sb1.append(userId).append(toolType).append(toolId).append(toolNum);
		String response = HttpClientBridge.sendToGameServer(REQ_URL,sb.toString(),sb1.toString());
		if(response==null){
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("删除出现异常,请查看日志！");
		}else{
			JSONObject jsonObject = JSONObject.fromObject(response);
			if (jsonObject.containsKey(HttpClientBridge.RETURN_RC)
					&& jsonObject.getInt(HttpClientBridge.RETURN_RC) != HttpClientBridge.RETURN_CODE){
				super.setErroCodeNum(Integer.parseInt(jsonObject.get(HttpClientBridge.FAIL).toString()));
				super.setErroDescrip("删除失败！");
			}
		}
		return SUCCESS; 
	 }

	public String getToolType() {
		return toolType;
	}


	public void setToolType(String toolType) {
		this.toolType = toolType;
	}


	public String getToolNum() {
		return toolNum;
	}


	public void setToolNum(String toolNum) {
		this.toolNum = toolNum;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToolId() {
		return toolId;
	}

	public void setToolId(String toolId) {
		this.toolId = toolId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	
}

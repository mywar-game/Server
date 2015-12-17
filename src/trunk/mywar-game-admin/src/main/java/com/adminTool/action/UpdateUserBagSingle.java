package com.adminTool.action;

import net.sf.json.JSONObject;

import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.constant.SystemConstant;

public class UpdateUserBagSingle extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 6900887493861005173L;
	private static final String REQ_URL = "delUserTool.do";
	private static final String REQ_URL1 = "addUserTool.do";
	
	private String userId;
	private String toolType;
	private String toolId;
	private String toolNum;
	private String flag = "update";

	public String execute() {

		int toolNumInt = Integer.parseInt(toolNum);
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		sb.append("&userId=").append(userId);
		sb.append("&toolType=").append(toolType);
		sb.append("&toolId=").append(toolId);
		sb.append("&toolNum=");
		sb1.append(userId).append(toolType).append(toolId);
		String response ;
		if(toolNumInt >= 0){
			sb.append(toolNum);
			sb1.append(toolNum);
			response = HttpClientBridge.sendToGameServer(REQ_URL,
					sb.toString(), sb1.toString());
		}else{
			sb.append(-toolNumInt);
			sb1.append(-toolNumInt);
			response = HttpClientBridge.sendToGameServer(REQ_URL1,
					sb.toString(), sb1.toString());
		}
		
		if (response == null) {
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("修改出现异常,请查看日志！");
		} else {
			JSONObject jsonObject = JSONObject.fromObject(response);
			if (jsonObject.containsKey(HttpClientBridge.RETURN_RC)
					&& jsonObject.getInt(HttpClientBridge.RETURN_RC) != HttpClientBridge.RETURN_CODE) {
				super.setErroCodeNum(Integer.parseInt(jsonObject.get(
						HttpClientBridge.FAIL).toString()));
				super.setErroDescrip("修改失败！");
			}
		}
		return SUCCESS;
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

	public String getToolNum() {
		return toolNum;
	}

	public void setToolNum(String toolNum) {
		this.toolNum = toolNum;
	}


	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getToolType() {
		return toolType;
	}

	public void setToolType(String toolType) {
		this.toolType = toolType;
	}
}

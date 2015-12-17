package com.adminTool.action;

import net.sf.json.JSONObject;

import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.constant.SystemConstant;

public class UpdateUserTreasureSingle extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 6900887493861005173L;
	private static final String REQ_URL = "updateUserTool.do";
	private String userId;
	private String toolId;
	private String toolNum;
	private String toolName;
	private String flag = "update";

	public String execute() {

		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		sb.append("&userId=").append(userId);
		sb.append("&toolId=").append(toolId);
		sb.append("&toolNum=").append(toolNum);
		sb1.append(userId).append(toolId).append(toolNum);
		String response = HttpClientBridge.sendToGameServer(REQ_URL,
				sb.toString(), sb1.toString());
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

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	

}

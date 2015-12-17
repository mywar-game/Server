package com.adminTool.action;

import java.util.Map;

import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.tool.ParseJason;

public class UserBan extends ALDAdminActionSupport {

	private static final long serialVersionUID = -9093543003146246705L;
	
	private static final String BAN_USER_URL = "banUser.do";
	
	private String isCommit = "F";
	private String userId;
	private String dueTime;
	
	public String execute() {
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		sb.append("&userId=").append(userId);
		sb.append("&dueTime=").append(dueTime);
		sb1.append(userId).append(dueTime);
		String response = HttpClientBridge.sendToGameServer(BAN_USER_URL,sb.toString(),sb1.toString());
		
		Map<String, String> map = ParseJason.jason2Map(response);
		String result = map.get("rt");
		if (!result.equals("1000")) {
			return ERROR;
		} else {
			return SUCCESS;
		}
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDueTime() {
		return dueTime;
	}

	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}
	
}

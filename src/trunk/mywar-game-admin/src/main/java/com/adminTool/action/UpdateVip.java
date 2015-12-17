package com.adminTool.action;

import java.util.Map;

import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.tool.ParseJason;

public class UpdateVip extends ALDAdminActionSupport {

	private static final long serialVersionUID = -2851434048944699320L;
	private static final String BAN_USER_URL = "assignVipLevel.do";

	private String userId;
	private String vipLevel;

	public String execute() {

		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		sb.append("&userId=").append(userId);
		sb.append("&vipLevel=").append(vipLevel);
		sb1.append(userId).append(vipLevel);
		String response = HttpClientBridge.sendToGameServer(BAN_USER_URL, sb.toString(), sb1.toString());

		Map<String, String> map = ParseJason.jason2Map(response);
		String result = map.get("rt");
		if (!result.equals("1000")) {
			return ERROR;
		} else {
			return SUCCESS;
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
	}
	
	
}

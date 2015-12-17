package com.adminTool.action;

import java.util.Map;

import com.adminTool.bo.User;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.tool.ParseJason;

public class UpdateVipSearch extends ALDAdminActionSupport{

	private static final long serialVersionUID = -7096649539151674368L;
	private static final String REQ_URL = "getUserInfo.do";

	private String isCommit = "F";
	private int lodoId;
	private String vipLevel;
	private User user;
	
	public String execute() {
		
		if ("F".equals(isCommit)) {
			return INPUT;
		} else {
			UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
			user = userService.findUserByLodoId(lodoId);
			
			StringBuilder sb = new StringBuilder();
			StringBuilder sb1 = new StringBuilder();
			if (user == null) {
				super.setErroDescrip("玩家不存在！");
				return INPUT;
			}
			sb.append("&userId=").append(user.getUserId());
			sb1.append(user.getUserId());
			String response = HttpClientBridge.sendToGameServer(REQ_URL,sb.toString(),sb1.toString());
		
			String result = ParseJason.jason2Map(response).get("result");
			Map<String, String> userInfo = ParseJason.jason2Map(result);
			vipLevel = userInfo.get("vipLevel");
			return SUCCESS;
		}
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public int getLodoId() {
		return lodoId;
	}

	public void setLodoId(int lodoId) {
		this.lodoId = lodoId;
	}

	public String getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}

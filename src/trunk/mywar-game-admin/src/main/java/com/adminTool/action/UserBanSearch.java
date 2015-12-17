package com.adminTool.action;

import java.util.Date;
import java.util.Map;

import com.adminTool.bo.User;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.tool.ParseJason;

public class UserBanSearch extends ALDAdminActionSupport{

	private static final long serialVersionUID = -9093543003146246705L;
	
	private static final String REQ_URL = "getUserInfo.do";
	
	private String isCommit = "F";
	
	private int lodoId;
	private User user;
	private int isBanned;
	
	public String execute() {
		
		if ("F".equals(isCommit)) {
			return INPUT;
		} else {
			UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
			user = userService.findUserByLodoId(lodoId);
			
			if (user == null) {
				super.setErroDescrip("玩家不存在！");
				return INPUT;
			}
			
			StringBuilder sb = new StringBuilder();
			StringBuilder sb1 = new StringBuilder();
			sb.append("&userId=").append(user.getUserId());
			sb1.append(user.getUserId());
			String response = HttpClientBridge.sendToGameServer(REQ_URL,sb.toString(),sb1.toString());
		
			String result = ParseJason.jason2Map(response).get("result");
			Map<String, String> userInfo = ParseJason.jason2Map(result);
			if(userInfo.get("dueTime")==null || userInfo.get("dueTime").equals("null")){
				isBanned = 0;
			}else{
				Date dueTime = DateUtil.stringtoDate(DateUtil.formatTime(Long.parseLong(userInfo.get("dueTime").toString())), DateUtil.FORMAT_ONE);
				if(dueTime.before(new Date())){
					isBanned = 0;
				}else{
					isBanned = 1;
				}
			}
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public int getIsBanned() {
		return isBanned;
	}

	public void setIsBanned(int isBanned) {
		this.isBanned = isBanned;
	}
	
}

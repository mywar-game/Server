package com.adminTool.action; 


import java.util.Map;

import net.sf.json.JSONObject;

import com.adminTool.bo.User;
import com.adminTool.msgbody.UserTreasureInfo;
import com.adminTool.service.MailToolService;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class GetUserBagSingle extends ALDAdminActionSupport implements
		ModelDriven<User> {

	private static final long serialVersionUID = 3144459422096382508L;
	private static final String REQ_URL = "getUserTool.do";
	
	private User user = new User(); 

	private UserTreasureInfo userBagSingle; 
	private String toolTypeId;
	private String toolName;
	private Map<String, String> tools;

	@SuppressWarnings({ "deprecation", "unchecked" })
	public String execute() {
		
		UserService userService = ServiceCacheFactory.getServiceCache()
				.getService(UserService.class); 
		MailToolService mailToolService =  ServiceCacheFactory.getServiceCache()
				.getService(MailToolService.class);
		
		tools = mailToolService.findSysTools();
		if (user.getUserId() == null && (user.getLodoId()==null ||user.getLodoId().intValue()==0)) {
			return SUCCESS;
		}

		if (user.getUserId() != null && !"".equals(user.getUserId())) {
			user = userService.findUserByUserId(user.getUserId()); 
		}else if (user.getLodoId().intValue()!=0) {
			user = userService.findUserByLodoId(user.getLodoId()); 
		}
		if (user == null) {
			super.setErroDescrip("玩家不存在！");
			return SUCCESS;
		}
		String userId = user.getUserId();
		toolName = tools.get(toolTypeId);
		String[] toolId = toolTypeId.split(",");

		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		sb.append("&userId=").append(userId);
		sb.append("&toolId=").append(toolId[1]);
		sb1.append(userId).append(toolId[1]);
		String response = HttpClientBridge.sendToGameServer(REQ_URL,sb.toString(),sb1.toString());
		if(response==null){
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("查询出现异常,请查看日志！");
		}else{
			JSONObject jsonObject = JSONObject.fromObject(response);
			if(jsonObject.containsKey(HttpClientBridge.FAIL)){
				super.setErroCodeNum(Integer.parseInt(jsonObject.get(HttpClientBridge.FAIL).toString()));
				super.setErroDescrip("查询失败！");
			}else{
				userBagSingle = (UserTreasureInfo)JSONObject.toBean((JSONObject)jsonObject.get(HttpClientBridge.SUCCESS), UserTreasureInfo.class);
				if(userBagSingle == null)
					super.setErroDescrip("玩家不存在此道具");
			}
		}
		
		return SUCCESS; 
	}

	@Override
	public User getModel() {
		return user; 
	}

	public User getUser() {
		return user; 
	}

	public void setUser(User user) {
		this.user = user; 
	}

	public UserTreasureInfo getUserBagSingle() {
		return userBagSingle;
	}

	public void setUserBagSingle(UserTreasureInfo userBagSingle) {
		this.userBagSingle = userBagSingle;
	}

	public String getToolTypeId() {
		return toolTypeId;
	}

	public void setToolTypeId(String toolTypeId) {
		this.toolTypeId = toolTypeId;
	}

	public Map<String, String> getTools() {
		return tools;
	}

	public void setTools(Map<String, String> tools) {
		this.tools = tools;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
}
package com.adminTool.action; 


import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.adminTool.bo.User;
import com.adminTool.msgbody.UserEquipmentSomeInfo;
import com.adminTool.msgbody.UserHeroSomeInfo;
import com.adminTool.msgbody.UserTreasureInfo;
import com.adminTool.service.MailToolService;
import com.adminTool.service.UserService;
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.SystemHeroService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class GetUserHeroSingle extends ALDAdminActionSupport implements
		ModelDriven<User> {

	private static final long serialVersionUID = 3144459422096382508L;
	private static final String REQ_URL = "getUserOneKindHeroList.do";
	private List<UserHeroSomeInfo> userHeroSomeInfoList;
	private User user = new User(); 
	private Map<Integer, String> heros;
	private String systemHeroId;

	@SuppressWarnings({ "deprecation", "unchecked" })
	public String execute() {
		
		UserService userService = ServiceCacheFactory.getServiceCache()
				.getService(UserService.class); 
		SystemHeroService heroService = ServiceCacheFactory.getServiceCache()
				.getService(SystemHeroService.class);
		
		heros = heroService.getHeroNameMap();
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

		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		sb.append("&userId=").append(userId);
		sb.append("&systemHeroId=").append(systemHeroId);
		sb1.append(userId).append(systemHeroId);
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
				userHeroSomeInfoList = JSONArray.toList((JSONArray)jsonObject.get(HttpClientBridge.SUCCESS), UserHeroSomeInfo.class);
				
				if(userHeroSomeInfoList.size() == 0)
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

	public List<UserHeroSomeInfo> getUserHeroSomeInfoList() {
		return userHeroSomeInfoList;
	}

	public void setUserHeroSomeInfoList(List<UserHeroSomeInfo> userHeroSomeInfoList) {
		this.userHeroSomeInfoList = userHeroSomeInfoList;
	}

	public Map<Integer, String> getHeros() {
		return heros;
	}

	public void setHeros(Map<Integer, String> heros) {
		this.heros = heros;
	}

	public String getSystemHeroId() {
		return systemHeroId;
	}

	public void setSystemHeroId(String systemHeroId) {
		this.systemHeroId = systemHeroId;
	}



	
}
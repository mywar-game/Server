package com.adminTool.action; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.adminTool.bo.User;
import com.adminTool.msgbody.UserTreasureInfo;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class GetUserTreasure extends ALDAdminActionSupport implements
		ModelDriven<User> {

	private static final long serialVersionUID = 5402666513242741232L; 
	private static final String REQ_URL = "getUserToolList.do";
	private User user = new User(); 

	private List<UserTreasureInfo> userTreasureList; 
	private Map<Integer, String> treasureIDNameMap = new HashMap<Integer, String>(); 

	@SuppressWarnings({ "deprecation", "unchecked" })
	public String execute() {
		if (user.getUserId() == null && (user.getLodoId()==null ||user.getLodoId().intValue()==0)) {
			return SUCCESS; 
		}
		UserService userService = ServiceCacheFactory.getServiceCache()
				.getService(UserService.class); 

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
		sb1.append(userId);
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
				userTreasureList = JSONArray.toList((JSONArray)jsonObject.get(HttpClientBridge.SUCCESS), UserTreasureInfo.class);
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

	public void setUserTreasureList(List<UserTreasureInfo> userTreasureList) {
		this.userTreasureList = userTreasureList; 
	}

	public List<UserTreasureInfo> getUserTreasureList() {
		return userTreasureList; 
	}

	public void setTreasureIDNameMap(Map<Integer, String> treasureIDNameMap) {
		this.treasureIDNameMap = treasureIDNameMap; 
	}

	public Map<Integer, String> getTreasureIDNameMap() {
		return treasureIDNameMap; 
	}

}
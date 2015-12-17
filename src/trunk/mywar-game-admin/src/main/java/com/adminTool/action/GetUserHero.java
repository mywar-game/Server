package com.adminTool.action; 

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.adminTool.msgbody.UserHeroSomeInfo;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;

public class GetUserHero extends ALDAdminActionSupport {

	private static final long serialVersionUID = -5087344276787955930L;
	private static final String REQ_URL = "getUserHeroList.do";
	/**
	 * 玩家标识
	 */
	private int lodoId;
	
	private List<UserHeroSomeInfo> userHeroSomeInfoList; 
	
	/**
	 * 是否提交
	 */
	private String isCommit = "F";
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String execute() {
		
		if ("F".equals(isCommit)) {
			return SUCCESS;
		}
		if (lodoId==0) {
			super.setErroDescrip("玩家标识为空，请填写！");
			return SUCCESS;
		}
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class); 
		com.adminTool.bo.User user = userService.findUserByLodoId(lodoId); 
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
				userHeroSomeInfoList = JSONArray.toList((JSONArray)jsonObject.get(HttpClientBridge.SUCCESS), UserHeroSomeInfo.class);
			}
		}
		return SUCCESS; 
	}

	public List<UserHeroSomeInfo> getUserHeroSomeInfoList() {
		return userHeroSomeInfoList; 
	}

	public void setUserHeroSomeInfoList(List<UserHeroSomeInfo> userHeroSomeInfoList) {
		this.userHeroSomeInfoList = userHeroSomeInfoList; 
	}

	public int getLodoId() {
		return lodoId;
	}

	public void setLodoId(int lodoId) {
		this.lodoId = lodoId;
	}

	/**
	 * 获取 是否提交 
	 */
	public String getIsCommit() {
		return isCommit;
	}

	/**
	 * 设置 是否提交 
	 */
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
}
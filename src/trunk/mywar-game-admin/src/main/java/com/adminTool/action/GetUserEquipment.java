package com.adminTool.action; 


import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.adminTool.msgbody.UserEquipmentSomeInfo;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;
public class GetUserEquipment extends ALDAdminActionSupport {

	private static final long serialVersionUID = 8205451880896128078L;
	private static final String REQ_URL = "getUserEquipList.do";
	/**
	 * 玩家标识
	 */
	private int lodoId;
	
	/**
	 * 是否提交
	 */
	private String isCommit = "F";
	
	private List<UserEquipmentSomeInfo> userEquipmentSomeInfoList; 
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String execute() {
		if ("F".equals(isCommit)) {
			return SUCCESS;
		}
		
		if (lodoId==0) {
			super.setErroDescrip("角色名为空，请填写！");
			return SUCCESS;
		}
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class); 
		com.adminTool.bo.User user = userService.findUserByLodoId(lodoId); 
		if (user == null) {
			super.setErroDescrip("玩家不存在！");
			return SUCCESS;
		}
		String userId = user.getUserId();
		StringBuilder sb = new StringBuilder();//参数列表
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
				userEquipmentSomeInfoList = JSONArray.toList((JSONArray)jsonObject.get(HttpClientBridge.SUCCESS), UserEquipmentSomeInfo.class);
			}
		}
		return SUCCESS; 
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

	/**
	 * 获取 userEquipmentSomeInfoList 
	 */
	public List<UserEquipmentSomeInfo> getUserEquipmentSomeInfoList() {
		return userEquipmentSomeInfoList;
	}

	/**
	 * 设置 userEquipmentSomeInfoList 
	 */
	public void setUserEquipmentSomeInfoList(
			List<UserEquipmentSomeInfo> userEquipmentSomeInfoList) {
		this.userEquipmentSomeInfoList = userEquipmentSomeInfoList;
	}

}
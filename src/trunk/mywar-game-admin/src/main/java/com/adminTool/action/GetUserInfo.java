package com.adminTool.action; 


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.adminTool.bo.Partner;
import com.adminTool.msgbody.UserSomeInfo;
import com.adminTool.service.PartnerService;
import com.adminTool.service.UserService;
import com.dataconfig.service.UserPayService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.log.LogSystem;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserRegLog;
import com.log.service.UserRegLogService;

public class GetUserInfo extends ALDAdminActionSupport {

	/**  **/
	private static final long serialVersionUID = 909657051721473295L; 
	private static final String REQ_URL = "getUserInfo.do";

	/** 玩家信息列表 **/
	private List<UserSomeInfo> infoList; 
	/**玩家注册信息**/
	private Map<String, UserRegLog> userRegMap = new HashMap<String, UserRegLog>();;
	private Map<String, Integer> rechargeMap = new HashMap<String, Integer>();
	
	
	/**玩家标识**/
	private int lodoId; 
	/**玩家名**/
	private String userName;
	
	/**
	 * 是否提交
	 */
	private String isCommit = "F";
	private Map<String, Partner> partnerMap;
	
	public String execute() {
		
		if ((lodoId == 0) && (userName == null || userName.equals(""))) {
			return SUCCESS; 
		}
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class); 
		UserPayService userPayService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class); 
		UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class); 
		List<com.adminTool.bo.User> list = userService.findUserByLodoIdAndUserName(lodoId, userName);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		partnerMap = partnerService.findAllPartnerMap();
		if(list==null || list.size()==0){
			super.setErroDescrip("玩家不存在！");
			return SUCCESS;
		}
		infoList = new ArrayList<UserSomeInfo>();
		for(com.adminTool.bo.User user : list){
			StringBuilder sb = new StringBuilder();
			StringBuilder sb1 = new StringBuilder();
			sb.append("&userId=").append(user.getUserId());
			sb1.append(user.getUserId());
			String response = HttpClientBridge.sendToGameServer(REQ_URL,sb.toString(),sb1.toString());
			LogSystem.info("查询用户信息: response" + response);
			LogSystem.debug("查询用户信息: response" + response);
			
			if (response.equals("") || response==null) {
				super.setErroCodeNum(SystemConstant.FAIL_CODE);
				super.setErroDescrip("查询出现异常,请查看日志！");
			} else {
				JSONObject jsonObject = JSONObject.fromObject(response);
				if (jsonObject.containsKey(HttpClientBridge.RETURN_RC) 
						&& jsonObject.getInt(HttpClientBridge.RETURN_RC) != HttpClientBridge.RETURN_SUCCESS_CODE) {
					super.setErroCodeNum(Integer.parseInt(jsonObject.get(HttpClientBridge.FAIL).toString()));
					super.setErroDescrip("查询失败！");
				} else {
					// TODO 需要修改
					UserSomeInfo userSomeInfo = (UserSomeInfo)JSONObject.toBean((JSONObject)jsonObject.get(HttpClientBridge.SUCCESS), UserSomeInfo.class);
					infoList.add(userSomeInfo);
					rechargeMap.put(userSomeInfo.getUserId(), userPayService.getPayAmountByUserIds(userSomeInfo.getUserId()));
					UserRegLog ll = userRegLogService.findUserRegLogByUserId(userSomeInfo.getUserId());
					String channel = ll.getChannel();
					Partner partner = partnerMap.get(channel);
					if (partner != null) {
						ll.setChannelName(partnerMap.get(channel).getPName());
					} else {
						ll.setChannelName("未命名渠道号:" + channel);
					}				
					userRegMap.put(userSomeInfo.getUserId(), ll);
				}
			}
		}
		
		return SUCCESS; 
	}

	public Map<String, UserRegLog> getUserRegMap() {
		return userRegMap;
	}

	public void setUserRegMap(Map<String, UserRegLog> userRegMap) {
		this.userRegMap = userRegMap;
	}

	public List<UserSomeInfo> getInfoList() {
		return infoList;
	}

	public Map<String, Integer> getRechargeMap() {
		return rechargeMap;
	}

	public void setRechargeMap(Map<String, Integer> rechargeMap) {
		this.rechargeMap = rechargeMap;
	}

	public void setInfoList(List<UserSomeInfo> infoList) {
		this.infoList = infoList;
	}

	public int getLodoId() {
		return lodoId;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setLodoId(int lodoId) {
		this.lodoId = lodoId;
	}
}
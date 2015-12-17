package com.adminTool.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.adminTool.bo.User;
import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ReqUserInfo;
import com.adminTool.msgbody.UserSomeInfo;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.constant.UserGoldLogCategory;
import com.log.service.UserGoldLogService;
import com.log.service.UserLogoutLogService;
import com.log.service.UserPayHistoryLogService;
import com.log.service.UserResourceLogService;

/**
 * 经济分析
 * @author hzy
 * 2012-7-31
 */
public class EconomicAnalysis extends ALDAdminActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;

	/**  */
	private String isSearch = "F";
	
	/**  */
	private Map<String, Long> moneyOutputMap;
	
	/**  */
	private Map<String, Long> moneyConsumeMap;
	
	/**  */
	private Map<Integer, String> diamondReceiveMap;
	
	/**  */
	private Map<Integer, String> diamondConsumeMap;
	
	/**  */
	private User user = new User();
	
	/**  */
	private Map<String, Integer> userInfoMap = new HashMap<String, Integer>();
	
	@Override
	public String execute() throws Exception {
		UserGoldLogService userGoldLogService = ServiceCacheFactory.getServiceCache().getService(UserGoldLogService.class);
		UserResourceLogService userResourceLogService = ServiceCacheFactory.getServiceCache().getService(UserResourceLogService.class);
		
		moneyOutputMap = userResourceLogService.findOperationAndMoneyAmountMap(user, 1);
		moneyConsumeMap = userResourceLogService.findOperationAndMoneyAmountMap(user, 2);
		
		diamondReceiveMap = userGoldLogService.findEveryTypeAmount(UserGoldLogCategory.RECEIVE, user, null);
		diamondConsumeMap = userGoldLogService.findEveryTypeAmount(UserGoldLogCategory.CONSUME, user, null);
		//搜索,填充userInfoMap
		if ("T".equals(isSearch)) {
			UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
			user = userService.findUserByCondition(null, user.getUserName(), user.getName());
			if (user == null) {
				super.setErroDescrip("没有此玩家");
				userInfoMap = null;
				return ERROR;
			}
			ReqUserInfo reqUserInfo = new ReqUserInfo(); 
			reqUserInfo.setUserId(user.getUserId() + "");
			Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.GET_USER_INFO, reqUserInfo, UserSomeInfo.class);
			if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
				CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
				super.setErroCodeNum(commomMsgBody.getErrorCode());
				super.setErroDescrip(commomMsgBody.getErrorDescription()+" 查看用户信息失败！");
				return ERROR;
			}
			UserSomeInfo userSomeInfo = (UserSomeInfo)msg.getMsgBody();
			userInfoMap.put("level", userSomeInfo.getLevel());
			userInfoMap.put("money", (int)userSomeInfo.getGold());
			userInfoMap.put("diamond", userSomeInfo.getMoney());
			
			UserLogoutLogService userLogoutLogService = ServiceCacheFactory.getServiceCache().getService(UserLogoutLogService.class);
			Integer totalLiveMinute = userLogoutLogService.getTotalLiveMinuteByUserId(user.getUserId());
			userInfoMap.put("totalLiveHours", totalLiveMinute / 60 + 1);
			
			UserPayHistoryLogService userPayHistoryLogService = ServiceCacheFactory.getServiceCache().getService(UserPayHistoryLogService.class);
			Integer payAmount = userPayHistoryLogService.getPayAmountByUserIds(user.getUserId() + "");
			userInfoMap.put("payAmount", payAmount);
			
			Iterator<Entry<Integer, String>> ite = diamondReceiveMap.entrySet().iterator();
			int amount = 0;
			while (ite.hasNext()) {
				Entry<Integer, String> entry = ite.next();
				amount += Integer.valueOf(entry.getValue().split("_")[0]);
			}
			userInfoMap.put("getDiamondAmountInGame", amount);
		} else {
			userInfoMap = null;
		}
		return SUCCESS;
	}

	/**
	 * @return the isSearch
	 */
	public String getIsSearch() {
		return isSearch;
	}

	/**
	 * @param isSearch the isSearch to set
	 */
	public void setIsSearch(String isSearch) {
		this.isSearch = isSearch;
	}

	/**
	 * @return the moneyOutputMap
	 */
	public Map<String, Long> getMoneyOutputMap() {
		return moneyOutputMap;
	}

	/**
	 * @param moneyOutputMap the moneyOutputMap to set
	 */
	public void setMoneyOutputMap(Map<String, Long> moneyOutputMap) {
		this.moneyOutputMap = moneyOutputMap;
	}

	/**
	 * @return the moneyConsumeMap
	 */
	public Map<String, Long> getMoneyConsumeMap() {
		return moneyConsumeMap;
	}

	/**
	 * @param moneyConsumeMap the moneyConsumeMap to set
	 */
	public void setMoneyConsumeMap(Map<String, Long> moneyConsumeMap) {
		this.moneyConsumeMap = moneyConsumeMap;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the userInfoMap
	 */
	public Map<String, Integer> getUserInfoMap() {
		return userInfoMap;
	}

	/**
	 * @param userInfoMap the userInfoMap to set
	 */
	public void setUserInfoMap(Map<String, Integer> userInfoMap) {
		this.userInfoMap = userInfoMap;
	}

	public void setDiamondReceiveMap(Map<Integer, String> diamondReceiveMap) {
		this.diamondReceiveMap = diamondReceiveMap;
	}

	public Map<Integer, String> getDiamondReceiveMap() {
		return diamondReceiveMap;
	}

	public void setDiamondConsumeMap(Map<Integer, String> diamondConsumeMap) {
		this.diamondConsumeMap = diamondConsumeMap;
	}

	public Map<Integer, String> getDiamondConsumeMap() {
		return diamondConsumeMap;
	}
}

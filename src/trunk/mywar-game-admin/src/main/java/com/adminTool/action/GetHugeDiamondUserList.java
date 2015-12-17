package com.adminTool.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adminTool.bean.UserBean;
import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ResGetHugeDiamondUserList;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.UserGoldLogService;
import com.log.service.UserPayHistoryLogService;

/**
 * 巨额钻石玩家列表
 * @author hzy
 * 2012-10-24
 */
public class GetHugeDiamondUserList extends ALDAdminActionSupport {

	/** * */
	private static final long serialVersionUID = 7056645036000806163L;

	private List<UserBean> userBeanList;
	
	public String execute() {
//		long a = System.currentTimeMillis();
		UserGoldLogService userGoldLogService =ServiceCacheFactory.getServiceCache().getService(UserGoldLogService.class);
		UserPayHistoryLogService userPayHistoryLogService = ServiceCacheFactory.getServiceCache().getService(UserPayHistoryLogService.class); 
		
		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.GET_HUGE_DIAMOND_USER_LIST, new CommomMsgBody(), ResGetHugeDiamondUserList.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription());
			return SUCCESS;
		}
		userBeanList = ((ResGetHugeDiamondUserList)msg.getMsgBody()).getUserBeanList();
		
		StringBuffer userIds = new StringBuffer();
		if (userBeanList != null && userBeanList.size() > 0) {
			userIds.append(userBeanList.get(0).getUserId());
			for (int i = 1; i < userBeanList.size(); i++) {
				userIds.append(",").append(userBeanList.get(i).getUserId());
			}
		}
		if (userIds.length() > 0) {
			Map<Long, Date> userIdAndLastConsumeTimeMap = userGoldLogService.findUserIdAndLastConsumeTimeMap(userIds.toString());
			Map<Long, Integer> userIdAndPayAmountMap = userPayHistoryLogService.findUserIdAndPayAmountMap(userIds.toString());
			for (UserBean userBean : userBeanList) {
				//将LastBeAttackCityTime当做最好一次消费时间使用下。。。
				if (userIdAndLastConsumeTimeMap != null && userIdAndLastConsumeTimeMap.size() > 0) {
					userBean.setLastBeAttackCityTime(userIdAndLastConsumeTimeMap.get(userBean.getUserId()));
				} else {
					userBean.setLastBeAttackCityTime(null);
				}
				//将silver当做充值总额用下
				if (userIdAndPayAmountMap != null && userIdAndPayAmountMap.size() > 0 && userIdAndPayAmountMap.get(userBean.getUserId()) != null) {
					userBean.setSilver(userIdAndPayAmountMap.get(userBean.getUserId()));
				} else {
					userBean.setSilver(0);
				}
			}
		}
//		System.out.println("yyyyyyyyyyyyyyyyyyyyyyy "+(System.currentTimeMillis() - a));
		return SUCCESS;
	}

	public void setUserBeanList(List<UserBean> userBeanList) {
		this.userBeanList = userBeanList;
	}

	public List<UserBean> getUserBeanList() {
		return userBeanList;
	}
}

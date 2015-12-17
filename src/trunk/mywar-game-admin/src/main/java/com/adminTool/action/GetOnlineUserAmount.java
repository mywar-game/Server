package com.adminTool.action;

import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserOnlineLog;
import com.log.service.UserOnlineLogService;

/**
 * 获取当前服务器的在线人数
 * @author hzy
 * 2012-7-26
 */
public class GetOnlineUserAmount extends ALDAdminActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;
	
	/** 在线人数 */
	private Integer onlineAmount;
	
	@Override
	public String execute() {
		UserOnlineLogService userOnlineLogService = ServiceCacheFactory.getServiceCache().getService(UserOnlineLogService.class);
		UserOnlineLog userOnlineLog = userOnlineLogService.findLastLog();
		if(userOnlineLog==null){
			onlineAmount = 0;
		}else{
			onlineAmount = userOnlineLog.getOnlineAmount();
		}
		return SUCCESS;
	}
	
	/**
	 * @return the onlineAmount
	 */
	public Integer getOnlineAmount() {
		return onlineAmount;
	}

	/**
	 * @param onlineAmount the onlineAmount to set
	 */
	public void setOnlineAmount(Integer onlineAmount) {
		this.onlineAmount = onlineAmount;
	}
}

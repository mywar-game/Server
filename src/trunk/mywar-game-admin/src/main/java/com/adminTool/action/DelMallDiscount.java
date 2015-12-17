package com.adminTool.action;

import com.adminTool.bo.MallDiscount;
import com.adminTool.service.MallDiscountItemService;
import com.adminTool.service.MallDiscountService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.TGameServer;
import com.system.service.TGameServerService;

public class DelMallDiscount extends ALDAdminActionSupport {
	private static final String DEL_ACTIVITY_URL = "delActivity.do";
	private static final String DEL_ITEMS_URL = "delItems.do";
	
	private static final long serialVersionUID = 1L;
	private String activityId;
	private String delHistory = "F";
	
	 public String execute() { 
		MallDiscountService mds = ServiceCacheFactory.getServiceCache().getService(MallDiscountService.class);
		TGameServerService tgs = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		MallDiscountItemService mdis = ServiceCacheFactory.getServiceCache().getService(MallDiscountItemService.class);
		 
		if ("F".equals(delHistory)) {
		// 从游戏数据库中删除活动信息和打折商品信息
			MallDiscount discount = mds.findOneMallDiscount(activityId);
			String[] servers = discount.getServers().split(", ");
			for (String server : servers) {
				TGameServer gameServer = tgs.findOneTGameServer(Integer.valueOf(server));
				if (gameServer == null)
					continue;
				
				delActivity(discount.getActivityId(), gameServer);
				delItems(discount.getActivityId(), gameServer);
			}
			
			discount.setStatus(2);
			mds.updateMallDiscount(discount);
		
//			mds.deleteMallDiscount(activityId);
//			mdis.deleteMallDiscountItems(activityId);
			return SUCCESS;
		} else {
			String[] ids = activityId.split(", ");
			for (String id : ids) {
				mds.deleteMallDiscount(id);
				mdis.deleteMallDiscountItems(id);
			}
			return SUCCESS;
		}
	 }
	 
	private String delActivity(String activityId, TGameServer gameServer) {
		String param = "&activityId=" + activityId;
		String response = HttpClientBridge.sendToGameServer(DEL_ACTIVITY_URL, param, activityId, gameServer);
		if (response == null) {
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("发送出现异常,请查看日志！");
			return ERROR;
		} else {
			return SUCCESS;
		}
	}
	
	private String delItems(String activityId, TGameServer gameServer) {
		String param = "&activityId=" + activityId;
		String response = HttpClientBridge.sendToGameServer(DEL_ITEMS_URL, param, activityId, gameServer);
		
		if (response == null) {
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("发送出现异常,请查看日志！");
			return ERROR;
		} else {
			return SUCCESS;
		}
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getDelHistory() {
		return delHistory;
	}

	public void setDelHistory(String delHistory) {
		this.delHistory = delHistory;
	}
	 
}

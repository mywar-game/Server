package com.adminTool.action;

import com.adminTool.service.ActivityTaskRewardService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
/**
 * 删除活跃度奖励配置 
 * @author Administrator
 *
 */
public class DelVatality extends ALDAdminActionSupport {

	private static final long serialVersionUID = -9125367829403257465L;
	private Integer activityTaskRewardId;
	
	public Integer getActivityTaskRewardId() {
		return activityTaskRewardId;
	}

	public void setActivityTaskRewardId(Integer activityTaskRewardId) {
		this.activityTaskRewardId = activityTaskRewardId;
	}

	public void executeDel() {
		ActivityTaskRewardService service = ServiceCacheFactory.getServiceCache()
				.getService(ActivityTaskRewardService.class);
		service.deleteByTaskRewardId(activityTaskRewardId);
	}
}

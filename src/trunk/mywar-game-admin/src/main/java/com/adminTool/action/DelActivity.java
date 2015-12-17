package com.adminTool.action;

import com.adminTool.constant.ActivityConstant;
import com.adminTool.service.ActivityService;
import com.adminTool.service.OncePayRewardService;
import com.adminTool.service.ToolExchangeService;
import com.adminTool.service.TotalDayPayRewardService;
import com.adminTool.service.TotalPayRewardService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 删除活动
 * 
 * @author yezp
 */
public class DelActivity extends ALDAdminActionSupport {

	private static final long serialVersionUID = -7246269358589095533L;

	private Integer activityId;
	private Integer activityType;

	public void executeDel() {
		ActivityService activityService = ServiceCacheFactory.getServiceCache()
				.getService(ActivityService.class);
		activityService.updateDelStatus(activityId);
		/*activityService.delActivity(activityId);*/

		// TODO 删除相关的活动配置
		/*switch (activityType) {
		case ActivityConstant.ACTIVITY_TYPE_TOTALPAYREWARD: {
			TotalPayRewardService service = ServiceCacheFactory
					.getServiceCache().getService(TotalPayRewardService.class);
			service.delTotalPayRewardByActivityId(activityId);
			break;
		}
		case ActivityConstant.ACTIVITY_TYPE_ONCEPAYREWARD: {
			OncePayRewardService service = ServiceCacheFactory
					.getServiceCache().getService(OncePayRewardService.class);
			service.delOncePayRewardByActivityId(activityId);
			break;
		}
		case ActivityConstant.ACTIVITY_TYPE_TOOLEXCHANGE: {
			ToolExchangeService service = ServiceCacheFactory.getServiceCache()
					.getService(ToolExchangeService.class);
			service.delToolExchangeByActivityId(activityId);
			break;
		}
		case ActivityConstant.ACTIVITY_TYPE_DAYPAYREWARD: {
			TotalDayPayRewardService service = ServiceCacheFactory
					.getServiceCache().getService(
							TotalDayPayRewardService.class);
			service.delTotalDayPayReward();
			break;
		}
		default:
			break;
		}*/
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

}

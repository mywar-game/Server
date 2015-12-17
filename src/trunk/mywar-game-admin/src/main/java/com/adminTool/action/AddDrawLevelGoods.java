package com.adminTool.action;

import com.adminTool.bo.ActivityDrawLevelGoods;
import com.adminTool.service.ActivityDrawLevelGoodsService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加等级抽奖替换物品
 * 
 * @author yezp
 */
public class AddDrawLevelGoods extends ALDAdminActionSupport implements
		ModelDriven<ActivityDrawLevelGoods> {

	private static final long serialVersionUID = -1215745500520166170L;

	private ActivityDrawLevelGoods activityDrawLevelGoods = new ActivityDrawLevelGoods();
	private String isCommit = "F";
	private String activityId;

	public String execute() {
		if (isCommit.equals("F")) {
			return INPUT;
		}

		ActivityDrawLevelGoodsService service = ServiceCacheFactory
				.getServiceCache().getService(
						ActivityDrawLevelGoodsService.class);
		service.addDrawLevelGoods(activityDrawLevelGoods);

		return SUCCESS;
	}

	@Override
	public ActivityDrawLevelGoods getModel() {
		// TODO Auto-generated method stub
		return activityDrawLevelGoods;
	}

	public ActivityDrawLevelGoods getActivityDrawLevelGoods() {
		return activityDrawLevelGoods;
	}

	public void setActivityDrawLevelGoods(
			ActivityDrawLevelGoods activityDrawLevelGoods) {
		this.activityDrawLevelGoods = activityDrawLevelGoods;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}

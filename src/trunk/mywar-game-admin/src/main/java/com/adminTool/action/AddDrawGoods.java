package com.adminTool.action;

import com.adminTool.bo.ActivityDrawGoods;
import com.adminTool.service.ActivityDrawGoodsService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加抽奖物品
 * 
 * @author yezp
 */
public class AddDrawGoods extends ALDAdminActionSupport implements
		ModelDriven<ActivityDrawGoods> {

	private static final long serialVersionUID = -875353065928075107L;
	private ActivityDrawGoods drawGoods = new ActivityDrawGoods();
	private String activityId;
	private String isCommit = "F";

	public String execute() {
		if (isCommit.equals("F")) {
			return INPUT;
		}

		ActivityDrawGoodsService service = ServiceCacheFactory
				.getServiceCache().getService(ActivityDrawGoodsService.class);
		service.addDrawGoods(drawGoods);

		return SUCCESS;
	}

	public ActivityDrawGoods getDrawGoods() {
		return drawGoods;
	}

	public void setDrawGoods(ActivityDrawGoods drawGoods) {
		this.drawGoods = drawGoods;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	@Override
	public ActivityDrawGoods getModel() {
		// TODO Auto-generated method stub
		return drawGoods;
	}

}

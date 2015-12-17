package com.adminTool.action;

import com.adminTool.bo.ActivityDrawLevelGoods;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.ActivityDrawLevelGoodsService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 修改等级抽奖替换物品
 * 
 * @author yezp
 */
public class UpdateDrawLevelGoods extends ALDAdminActionSupport implements
		ModelDriven<ActivityDrawLevelGoods> {

	private static final long serialVersionUID = 1449545182669359935L;
	private ActivityDrawLevelGoods activityDrawLevelGoods = new ActivityDrawLevelGoods();
	private String isCommit = "F";
	private String activityId;

	public String execute() {
		ActivityDrawLevelGoodsService service = ServiceCacheFactory
				.getServiceCache().getService(
						ActivityDrawLevelGoodsService.class);
		if ("F".equals(isCommit)) {
			activityDrawLevelGoods = service
					.findOneDrawLevelGoods(activityDrawLevelGoods.getId());
			ToolTypeConstant typeConstant = new ToolTypeConstant();
			int toolType = activityDrawLevelGoods.getToolType();
			int toolId = activityDrawLevelGoods.getToolId();

			activityDrawLevelGoods.setToolTypeString(typeConstant
					.getToolTypeNameMap().get(toolType));
			activityDrawLevelGoods.setToolString(typeConstant.getToolName(
					toolType, toolId));

			return INPUT;
		}

		service.updateDrawLevelGoods(activityDrawLevelGoods);
		return SUCCESS;
	}

	public ActivityDrawLevelGoods getActivityDrawLevelGoods() {
		return activityDrawLevelGoods;
	}

	public void setActivityDrawLevelGoods(
			ActivityDrawLevelGoods activityDrawLevelGoods) {
		this.activityDrawLevelGoods = activityDrawLevelGoods;
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
	public ActivityDrawLevelGoods getModel() {
		// TODO Auto-generated method stub
		return activityDrawLevelGoods;
	}

}

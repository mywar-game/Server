package com.adminTool.action;

import com.adminTool.bo.ActivityDrawGoods;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.ActivityDrawGoodsService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 修改抽奖物品
 * 
 * @author yezp
 */
public class UpdateDrawGoods extends ALDAdminActionSupport implements
		ModelDriven<ActivityDrawGoods> {

	private static final long serialVersionUID = -25868627900419217L;
	private ActivityDrawGoods drawGoods = new ActivityDrawGoods();
	private String isCommit = "F";
	private String activityId;

	public String execute() {
		ActivityDrawGoodsService service = ServiceCacheFactory
				.getServiceCache().getService(ActivityDrawGoodsService.class);
		if ("F".equals(isCommit)) {
			drawGoods = service.findOneDrawGoods(drawGoods.getGoodsId());

			int toolType = drawGoods.getToolType();
			int toolId = drawGoods.getToolId();

			ToolTypeConstant typeConstant = new ToolTypeConstant();
			drawGoods.setToolTypeName(typeConstant.getToolTypeNameMap().get(
					toolType));
			drawGoods.setToolName(typeConstant.getToolName(toolType, toolId));
			return INPUT;
		}

		service.updateDrawGoods(drawGoods);
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

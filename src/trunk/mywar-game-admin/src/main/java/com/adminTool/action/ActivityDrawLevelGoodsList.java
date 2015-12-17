package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.ActivityDrawLevelGoods;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.ActivityDrawLevelGoodsService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 抽奖等级替换物品
 * 
 * @author yezp
 */
public class ActivityDrawLevelGoodsList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 561528763792304882L;

	private List<ActivityDrawLevelGoods> drawLevelGoodsList;
	private String activityId;
	private String isCommit = "F";

	public String execute() {
		ActivityDrawLevelGoodsService service = ServiceCacheFactory
				.getServiceCache().getService(
						ActivityDrawLevelGoodsService.class);
		int activityIdInt = 0;
		if (activityId != null && !activityId.equals("")) {
			activityIdInt = Integer.parseInt(activityId);
		}

		IPage<ActivityDrawLevelGoods> iPage = service
				.findDrawLevelGoodsPageList(activityIdInt, super.getToPage(),
						ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (iPage == null) {
			return SUCCESS;
		}

		ToolTypeConstant typeConstant = new ToolTypeConstant();
		drawLevelGoodsList = (List<ActivityDrawLevelGoods>) iPage.getData();
		for (ActivityDrawLevelGoods levelGoods : drawLevelGoodsList) {
			int toolType = levelGoods.getToolType();
			int toolId = levelGoods.getToolId();			
			
			levelGoods.setToolTypeString(typeConstant.getToolTypeNameMap().get(toolType));
			levelGoods.setToolString(typeConstant.getToolName(toolType, toolId));
		}

		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		return SUCCESS;
	}

	public List<ActivityDrawLevelGoods> getDrawLevelGoodsList() {
		return drawLevelGoodsList;
	}

	public void setDrawLevelGoodsList(
			List<ActivityDrawLevelGoods> drawLevelGoodsList) {
		this.drawLevelGoodsList = drawLevelGoodsList;
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

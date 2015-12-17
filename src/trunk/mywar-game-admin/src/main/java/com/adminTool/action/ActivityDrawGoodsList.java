package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.ActivityDrawGoods;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.ActivityDrawGoodsService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 抽奖物品配置列表
 * 
 * @author yezp
 */
public class ActivityDrawGoodsList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -832831193291158696L;
	private Integer activityId;
	private List<ActivityDrawGoods> drawGoodsList;

	public String execute() {
		ActivityDrawGoodsService service = ServiceCacheFactory
				.getServiceCache().getService(ActivityDrawGoodsService.class);		
		IPage<ActivityDrawGoods> iPage = service.findDrawGoodsPageList(
				activityId, super.getToPage(),
				ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (iPage == null) {
			return SUCCESS;
		}

		ToolTypeConstant typeConstant = new ToolTypeConstant();
		drawGoodsList = (List<ActivityDrawGoods>) iPage.getData();
		for (ActivityDrawGoods drawGoods : drawGoodsList) {
			int toolId = drawGoods.getToolId();
			int toolType = drawGoods.getToolType();

			drawGoods.setToolTypeName(typeConstant.getToolTypeNameMap().get(
					toolType));
			drawGoods.setToolName(typeConstant.getToolName(toolType, toolId));
		}

		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		return SUCCESS;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public List<ActivityDrawGoods> getDrawGoodsList() {
		return drawGoodsList;
	}

	public void setDrawGoodsList(List<ActivityDrawGoods> drawGoodsList) {
		this.drawGoodsList = drawGoodsList;
	}

}

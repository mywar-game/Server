package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.ToolExchange;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.ToolExchangeService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 道具兑换配置列表
 * 
 * @author yezp
 */
public class ToolExchangeList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 4821998350468848722L;

	private List<ToolExchange> toolExchangeList;
	private String activityId;
	private String isCommit = "F";

	public String execute() {
		ToolExchangeService service = ServiceCacheFactory.getServiceCache()
				.getService(ToolExchangeService.class);

		int id = 0;
		if (activityId != null && !activityId.equals("")) {
			id = Integer.parseInt(activityId);
		}

		IPage<ToolExchange> iPage = service.findToolExchangePageList(id,
				super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (iPage == null) {
			return SUCCESS;
		}

		ToolTypeConstant typeConstant = new ToolTypeConstant();
		toolExchangeList = (List<ToolExchange>) iPage.getData();
		for (ToolExchange tool : toolExchangeList) {
			String showPreItems = typeConstant.getToolTypeAndName(tool
					.getPreExchangeItems());
			tool.setShowPreItems(showPreItems);

			String showPostItems = typeConstant.getToolTypeAndName(tool
					.getPostExchangeItems());
			tool.setShowPostItems(showPostItems);
		}

		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		return SUCCESS;
	}

	public List<ToolExchange> getToolExchangeList() {
		return toolExchangeList;
	}

	public void setToolExchangeList(List<ToolExchange> toolExchangeList) {
		this.toolExchangeList = toolExchangeList;
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

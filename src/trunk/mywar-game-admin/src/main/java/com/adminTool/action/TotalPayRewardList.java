package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.TotalPayReward;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.TotalPayRewardService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 累积充值活动配置列表
 * 
 * @author yezp
 */
public class TotalPayRewardList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 1150402260659921538L;

	private List<TotalPayReward> totalPayList;
	private String isCommit = "F";

	private String activityId;

	public String execute() {
		TotalPayRewardService service = ServiceCacheFactory.getServiceCache()
				.getService(TotalPayRewardService.class);

		int id = 0;
		if (activityId != null && !activityId.equals("")) {
			id = Integer.parseInt(activityId);
		}

		IPage<TotalPayReward> iPage = service.findTotalPayRewardPageList(id,
				super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);

		if (iPage == null) {
			return SUCCESS;
		}

		ToolTypeConstant typeConstant = new ToolTypeConstant();
		totalPayList = (List<TotalPayReward>) iPage.getData();
		for (TotalPayReward total : totalPayList) {
			String show = typeConstant.getToolTypeAndName(total.getRewards());
			total.setShowRewards(show);
		}

		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		return SUCCESS;
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

	public List<TotalPayReward> getTotalPayList() {
		return totalPayList;
	}

	public void setTotalPayList(List<TotalPayReward> totalPayList) {
		this.totalPayList = totalPayList;
	}

}

package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.TotalDayPayReward;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.TotalDayPayRewardService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 充值累积天数
 * 
 * @author yezp
 */
public class TotalDayPayRewardList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 8701925361409013734L;

	private List<TotalDayPayReward> totalDayPayRewardList;
	private String isCommit = "F";
	private int activityId;

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String execute() {
		TotalDayPayRewardService service = ServiceCacheFactory
				.getServiceCache().getService(TotalDayPayRewardService.class);

		IPage<TotalDayPayReward> iPage = service.findTotalDayPayRewardPageList(activityId,
				super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (iPage == null) {
			return SUCCESS;
		}

		ToolTypeConstant typeConstant = new ToolTypeConstant();
		totalDayPayRewardList = (List<TotalDayPayReward>) iPage.getData();
		for (TotalDayPayReward totalDay : totalDayPayRewardList) {
			String show = typeConstant.getToolTypeAndName(totalDay
					.getDropTool());
			totalDay.setShowDropTool(show);
		}

		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		return SUCCESS;
	}

	public List<TotalDayPayReward> getTotalDayPayRewardList() {
		return totalDayPayRewardList;
	}

	public void setTotalDayPayRewardList(
			List<TotalDayPayReward> totalDayPayRewardList) {
		this.totalDayPayRewardList = totalDayPayRewardList;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}

package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.OncePayReward;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.OncePayRewardService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 单笔充值配置列表
 * 
 * @author yezp
 */
public class OncePayRewardList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -9085115590221401885L;

	private List<OncePayReward> oncePayList;
	private String activityId;
	private String isCommit = "F";

	public String execute() {
		OncePayRewardService service = ServiceCacheFactory.getServiceCache()
				.getService(OncePayRewardService.class);

		int id = 0;
		if (activityId != null && !activityId.equals("")) {
			id = Integer.parseInt(activityId);
		}

		IPage<OncePayReward> iPage = service.findOncePayRewardPageList(id,
				super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (iPage == null) {
			return SUCCESS;
		}

		ToolTypeConstant typeConstant = new ToolTypeConstant();
		oncePayList = (List<OncePayReward>) iPage.getData();
		for (OncePayReward once : oncePayList) {
			String show = typeConstant.getToolTypeAndName(once.getRewards());
			once.setShowRewards(show);
		}

		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		return SUCCESS;
	}

	public List<OncePayReward> getOncePayList() {
		return oncePayList;
	}

	public void setOncePayList(List<OncePayReward> oncePayList) {
		this.oncePayList = oncePayList;
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

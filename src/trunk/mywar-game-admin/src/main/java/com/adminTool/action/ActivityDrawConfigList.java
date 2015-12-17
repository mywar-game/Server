package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.ActivityDrawConfig;
import com.adminTool.service.ActivityDrawConfigService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 活动抽奖次数配置
 * 
 * @author yezp
 */
public class ActivityDrawConfigList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -7802618368912370401L;

	private List<ActivityDrawConfig> drawConfigList;
	private String activityId;
	private String isCommit = "F";

	public String execute() {
		ActivityDrawConfigService service = ServiceCacheFactory
				.getServiceCache().getService(ActivityDrawConfigService.class);
		int activityIdInt = 0;
		if (activityId != null && !activityId.equals("")) {
			activityIdInt = Integer.parseInt(activityId);
		}

		IPage<ActivityDrawConfig> iPage = service.findDrawConfigPageList(
				activityIdInt, super.getToPage(),
				ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (iPage == null) {
			return SUCCESS;
		}
		
		drawConfigList = (List<ActivityDrawConfig>) iPage.getData();
		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		return SUCCESS;
	}

	public List<ActivityDrawConfig> getDrawConfigList() {
		return drawConfigList;
	}

	public void setDrawConfigList(List<ActivityDrawConfig> drawConfigList) {
		this.drawConfigList = drawConfigList;
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

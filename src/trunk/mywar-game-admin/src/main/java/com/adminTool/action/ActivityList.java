package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.Activity;
import com.adminTool.service.ActivityService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 活动列表
 * 
 * @author yezp
 */
public class ActivityList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -2950472069120240452L;

	private List<Activity> activityList;

	public String execute() {
		ActivityService activityService = ServiceCacheFactory.getServiceCache()
				.getService(ActivityService.class);
		IPage<Activity> iPage = activityService.findActivityPageList(
				super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);

		if (iPage == null) {
			activityList = new ArrayList<Activity>();
			return SUCCESS;
		}

		activityList = (List<Activity>) iPage.getData();
		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());

		return SUCCESS;
	}

	public List<Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}

}

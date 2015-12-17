package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.Activity;
import com.adminTool.constant.ActivityConstant;
import com.adminTool.service.ActivityService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加活动
 * 
 * @author yezp
 */
public class AddActivity extends ALDAdminActionSupport implements
		ModelDriven<Activity> {

	private static final long serialVersionUID = -3162910621167306794L;

	private Activity activity = new Activity();

	private String isCommit = "F";

	public String execute() {
		ActivityService activityService = ServiceCacheFactory.getServiceCache()
				.getService(ActivityService.class);

		if (isCommit.equals("F")) {
			Activity activityI = activityService.finLastActivity();
			if (activityI == null) {
				activity.setActivityId(ActivityConstant.ACTIVITY_ID_BEGIN_NUMBER);
			} else {
				activity.setActivityId(activityI.getActivityId() + 1);
			}

			return INPUT;
		}
		
		// 部分活动可重复
		switch (activity.getActivityType().intValue()) {
		case 3:
		case 4:
		case 5:
		case 6:
		case 18:
		case 19:
		case 31:
		case 32:
		case 33:
		case 38:
		case 41:
		case 100: {
			activityService.addActivity(activity);
			return SUCCESS;
		}
		default:
			break;
		}

		List<Activity> list = activityService.getActivityList(); 
		for (Activity a : list) {
			if (a.getActivityType().equals(activity.getActivityType())) {
				setErroDescrip("已存在该活动类型");
				return INPUT;
			}
		}

		activityService.addActivity(activity);
		return SUCCESS;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	@Override
	public Activity getModel() {
		// TODO Auto-generated method stub
		return activity;
	}

}

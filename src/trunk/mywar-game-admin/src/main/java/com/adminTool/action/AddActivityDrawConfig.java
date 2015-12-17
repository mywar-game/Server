package com.adminTool.action;

import com.adminTool.bo.ActivityDrawConfig;
import com.adminTool.service.ActivityDrawConfigService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加抽奖次数配置
 * 
 * @author yezp
 */
public class AddActivityDrawConfig extends ALDAdminActionSupport implements
		ModelDriven<ActivityDrawConfig> {

	private static final long serialVersionUID = 6269494629047420851L;
	private ActivityDrawConfig activityDrawConfig = new ActivityDrawConfig();
	private String isCommit = "F";
	private String activityId;

	private String[] timesArr;
	private String[] diamondArr;

	public String execute() {
		if (timesArr == null) {
			isCommit = "F";
			return SUCCESS;
		}

		ActivityDrawConfigService service = ServiceCacheFactory
				.getServiceCache().getService(ActivityDrawConfigService.class);
		service.delDrawConfigByActivityId(activityDrawConfig.getActivityId());
		
		for (int i = 0; i < timesArr.length; i++) {
			boolean mark = false;
			if (timesArr[i] != null && !timesArr.equals("")) {
				try {
					int times = Integer.parseInt(timesArr[i]);
					activityDrawConfig.setDrawTimes(times);
					mark = true;
				} catch (NumberFormatException e) {
					mark = false;
				}
			}
			
			if (mark && diamondArr[i] != null && !diamondArr[i].equals("")) {
				try {
					int diamond = Integer.parseInt(diamondArr[i]);
					activityDrawConfig.setDrawNeedDiamond(diamond);
					mark = true;
				} catch (NumberFormatException e) {
					mark = false;
				}
			}
			
			if (mark) {
				service.addDrawConfig(activityDrawConfig);
			}
		}		
		
		return SUCCESS;
	}

	public ActivityDrawConfig getActivityDrawConfig() {
		return activityDrawConfig;
	}

	public void setActivityDrawConfig(ActivityDrawConfig activityDrawConfig) {
		this.activityDrawConfig = activityDrawConfig;
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

	public String[] getTimesArr() {
		return timesArr;
	}

	public void setTimesArr(String[] timesArr) {
		this.timesArr = timesArr;
	}

	public String[] getDiamondArr() {
		return diamondArr;
	}

	public void setDiamondArr(String[] diamondArr) {
		this.diamondArr = diamondArr;
	}

	@Override
	public ActivityDrawConfig getModel() {
		// TODO Auto-generated method stub
		return activityDrawConfig;
	}

}

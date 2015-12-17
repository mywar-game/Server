package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.ActivityTaskReward;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.ActivityTaskRewardService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 活跃度奖励配置
 * @author Administrator
 *
 */
public class UpdateVitality extends ALDAdminActionSupport {

	private static final long serialVersionUID = 7808831706467342834L;

	private Boolean isCommit = false;
	private Integer[] activityTaskRewardIdArr;
	private Integer[] pointArr;
	private String[] rewardsArr;
	
	private List<ActivityTaskReward> activitTaskRewardList = new ArrayList<ActivityTaskReward>();
	
	@Override
	public String execute() {

		ActivityTaskRewardService activityTaskRewardService = ServiceCacheFactory.getServiceCache()
				.getService(ActivityTaskRewardService.class);
		if (isCommit == false) {
			activitTaskRewardList = activityTaskRewardService.findAll();
			ToolTypeConstant typeConstant = new ToolTypeConstant();
			if (activitTaskRewardList != null) {
				for (ActivityTaskReward s : activitTaskRewardList) {
					String show = typeConstant.getToolTypeAndName(s.getRewards());
					s.setDesc(show);
				}
			}
			return INPUT;
		} else {
			if (activityTaskRewardIdArr == null) {
				return INPUT;
			}
			
			activityTaskRewardService.deleteAll();
			for (int i = 0; i < activityTaskRewardIdArr.length; i++) {
				boolean mark = false;
				ActivityTaskReward task = new ActivityTaskReward();
				if (activityTaskRewardIdArr[i] != null && !activityTaskRewardIdArr[i].equals("")) {
					task.setActivityTaskRewardId(activityTaskRewardIdArr[i]);
					mark = true;
				} else {
					mark = false;
				}
				if (mark && pointArr[i] != null && !pointArr[i].equals("")) {
					task.setPoint(pointArr[i]);
					mark = true;
				} else {
					mark = false;
				}
				if (mark && rewardsArr[i] != null && !rewardsArr[i].equals("")) {
					task.setRewards(rewardsArr[i]);
				} else {
					mark = false;
				}

				if (mark) {
					activityTaskRewardService.save(task);
				}
			}
			activitTaskRewardList = activityTaskRewardService.findAll();
			ToolTypeConstant typeConstant = new ToolTypeConstant();
			if (activitTaskRewardList != null) {
				for (ActivityTaskReward s : activitTaskRewardList) {
					String show = typeConstant.getToolTypeAndName(s.getRewards());
					s.setDesc(show);
				}
			}
			return SUCCESS;
		}
	}

	public Boolean getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(Boolean isCommit) {
		this.isCommit = isCommit;
	}

	public List<ActivityTaskReward> getActivitTaskRewardList() {
		return activitTaskRewardList;
	}

	public void setActivitTaskRewardList(
			List<ActivityTaskReward> activitTaskRewardList) {
		this.activitTaskRewardList = activitTaskRewardList;
	}

	public Integer[] getActivityTaskRewardIdArr() {
		return activityTaskRewardIdArr;
	}

	public void setActivityTaskRewardIdArr(Integer[] activityTaskRewardIdArr) {
		this.activityTaskRewardIdArr = activityTaskRewardIdArr;
	}

	public Integer[] getPointArr() {
		return pointArr;
	}

	public void setPointArr(Integer[] pointArr) {
		this.pointArr = pointArr;
	}

	public String[] getRewardsArr() {
		return rewardsArr;
	}

	public void setRewardsArr(String[] rewardsArr) {
		this.rewardsArr = rewardsArr;
	}

}

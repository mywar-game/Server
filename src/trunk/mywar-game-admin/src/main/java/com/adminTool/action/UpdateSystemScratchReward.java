package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemActivityScratchReward;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.SystemActivityScratchRewardService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 热血刮刮乐
 * @author Administrator
 *
 */
public class UpdateSystemScratchReward extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 1L;

	public List<SystemActivityScratchReward> systemActivityScratchRewardList = new ArrayList<SystemActivityScratchReward>();
	
	public String isCommit = "F";
	
	private Integer[] id;
	private Integer[] activityIds;
	private String[] rewards;
	private Integer[] lowerNum;
	private Integer[] upperNum;
	private Integer[] isAmend;
	
	private int activityId;
	
	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public Integer[] getActivityIds() {
		return activityIds;
	}

	public void setActivityIds(Integer[] activityIds) {
		this.activityIds = activityIds;
	}

	@Override
	public String execute() {
		
		SystemActivityScratchRewardService systemActivityScratchRewardService = ServiceCacheFactory.getServiceCache().getService(SystemActivityScratchRewardService.class);
		if (isCommit.equalsIgnoreCase("F")) {
			systemActivityScratchRewardList = systemActivityScratchRewardService.findAll(activityId);
			ToolTypeConstant typeConstant = new ToolTypeConstant();
			
			for (SystemActivityScratchReward re : systemActivityScratchRewardList) {
				String show = typeConstant.getToolTypeAndName(re.getRewards());
				re.setRewardsDesc(show);
			}
		} else {
			for (int i = 0; i < id.length; i++) {
				SystemActivityScratchReward reward = new SystemActivityScratchReward();
				reward.setId(id[i]);
				if (activityIds.length > i) {
					reward.setActivityId(activityIds[i]);
				}
				if (rewards.length > i) {
					reward.setRewards(rewards[i]);
				}
				if (lowerNum.length > i) {
					reward.setLowerNum(lowerNum[i]);
				}
				if (upperNum.length > i) {
					reward.setUpperNum(upperNum[i]);
				}
				if (isAmend.length > i) {
					reward.setIsAmend(isAmend[i]);
				}
				systemActivityScratchRewardService.update(reward);
			}
		}
		return SUCCESS;
	}


	public List<SystemActivityScratchReward> getSystemActivityScratchRewardList() {
		return systemActivityScratchRewardList;
	}

	public void setSystemActivityScratchRewardList(
			List<SystemActivityScratchReward> systemActivityScratchRewardList) {
		this.systemActivityScratchRewardList = systemActivityScratchRewardList;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public Integer[] getId() {
		return id;
	}

	public void setId(Integer[] id) {
		this.id = id;
	}

	public String[] getRewards() {
		return rewards;
	}

	public void setRewards(String[] rewards) {
		this.rewards = rewards;
	}

	public Integer[] getLowerNum() {
		return lowerNum;
	}

	public void setLowerNum(Integer[] lowerNum) {
		this.lowerNum = lowerNum;
	}

	public Integer[] getUpperNum() {
		return upperNum;
	}

	public void setUpperNum(Integer[] upperNum) {
		this.upperNum = upperNum;
	}
	public Integer[] getIsAmend() {
		return isAmend;
	}
	public void setIsAmend(Integer[] isAmend) {
		this.isAmend = isAmend;
	}
}

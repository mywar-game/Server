package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemTotalConsumeReward;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.SystemTotalConsumeRewardService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 累积消费配置
 * @author Administrator
 *
 */
public class UpdateSystemTotalConsumeReward extends ALDAdminPageActionSupport {

	private String isCommit = "F";
	private Integer[] id;
	private Integer[] diamond;
	private String[] rewards;
	private Integer activityId;
	
	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	private String isDelete = "F";
	private Integer did;
	
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}
	private String[] rewardsDesc;
	private List<SystemTotalConsumeReward> systemTotalConsumeRewardList = new ArrayList<SystemTotalConsumeReward>();
	
	public List<SystemTotalConsumeReward> getSystemTotalConsumeRewardList() {
		return systemTotalConsumeRewardList;
	}


	public void setSystemTotalConsumeRewardList(
			List<SystemTotalConsumeReward> systemTotalConsumeRewardList) {
		this.systemTotalConsumeRewardList = systemTotalConsumeRewardList;
	}


	public Integer[] getId() {
		return id;
	}


	public void setId(Integer[] id) {
		this.id = id;
	}


	public Integer[] getDiamond() {
		return diamond;
	}


	public void setDiamond(Integer[] diamond) {
		this.diamond = diamond;
	}


	public String[] getRewards() {
		return rewards;
	}


	public void setRewards(String[] rewards) {
		this.rewards = rewards;
	}


	public String[] getRewardsDesc() {
		return rewardsDesc;
	}


	public void setRewardsDesc(String[] rewardsDesc) {
		this.rewardsDesc = rewardsDesc;
	}


	@Override
	public String execute() {
		
		SystemTotalConsumeRewardService service = ServiceCacheFactory.getServiceCache().getService(SystemTotalConsumeRewardService.class);
		
		if (isCommit.equalsIgnoreCase("T")) {
			
			if (isDelete.equalsIgnoreCase("T")) {
				service.deleteById(did);
			} else {
				service.deleteAll(activityId);
				for (int i = 0; i < diamond.length; i++) {
					SystemTotalConsumeReward reward = new SystemTotalConsumeReward();
					reward.setDiamond(diamond[i]);
					if (rewards.length > i) {
						reward.setRewards(rewards[i]);
					}
					reward.setActivityId(activityId);
					service.save(reward);
				}
			}
			
		}
		systemTotalConsumeRewardList = service.findAll(activityId);
		for (SystemTotalConsumeReward reward : systemTotalConsumeRewardList) {
			ToolTypeConstant typeConstant = new ToolTypeConstant();
			if (reward.getRewards() != null) {
				String show = typeConstant.getToolTypeAndName(reward.getRewards());
				reward.setRewardsDesc(show);
			}
		}
		return SUCCESS;
	}
	
	
	public String getIsCommit() {
		return isCommit;
	}
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
	
}

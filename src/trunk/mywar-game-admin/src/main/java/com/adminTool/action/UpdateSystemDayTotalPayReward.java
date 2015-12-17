package com.adminTool.action;

import com.adminTool.bo.SystemDayTotalPayReward;
import com.adminTool.service.SystemDayTotalPayRewardService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UpdateSystemDayTotalPayReward extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 1L;
	private int id;
	private int activityId;
	private int payMoney;
	private String rewards;
	private String isCommit = "F";
	
	@Override
	public String execute() {
		
		SystemDayTotalPayRewardService service = ServiceCacheFactory.getServiceCache().getService(SystemDayTotalPayRewardService.class);
		
		if (id != 0) {
			SystemDayTotalPayReward s = new SystemDayTotalPayReward();
			s.setId(id);
			s.setActivityId(activityId);
			s.setPayMoney(payMoney);
			s.setRewards(rewards);
			service.update(s);
		} else {
			int maxId = service.getMaxId();
			SystemDayTotalPayReward s = new SystemDayTotalPayReward();
			s.setId(maxId + 1);
			s.setActivityId(activityId);
			s.setPayMoney(payMoney);
			s.setRewards(rewards);
			service.insert(s);
		}
		isCommit = "T";
		return SUCCESS;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(int payMoney) {
		this.payMoney = payMoney;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}
	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}

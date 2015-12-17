package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.SystemDayTotalPayReward;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.SystemDayTotalPayRewardService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class SystemDayTotalPayRewardList extends ALDAdminActionSupport {

	private static final long serialVersionUID = 1L;
	private List<SystemDayTotalPayReward> list;
	private int id = -1;
	private int activityId = -1;

	@Override
	public String execute() {
		
		SystemDayTotalPayRewardService service = ServiceCacheFactory.getServiceCache().getService(SystemDayTotalPayRewardService.class);
		if (id != -1) {
			list = service.getById(activityId, id);
			if (list != null) {
				for (SystemDayTotalPayReward s : list) {
					ToolTypeConstant typeConstant = new ToolTypeConstant();
					String showRewardsStr = typeConstant.getToolTypeAndName(s.getRewards());
					s.setDesc(showRewardsStr);
				}
			}
			return INPUT;
		} else {
			list = service.getList(activityId);
		}
		
		if (list != null) {
			for (SystemDayTotalPayReward s : list) {
				ToolTypeConstant typeConstant = new ToolTypeConstant();
				String showRewardsStr = typeConstant.getToolTypeAndName(s.getRewards());
				s.setDesc(showRewardsStr);
			}
		}
		return SUCCESS;
	}
	
	public List<SystemDayTotalPayReward> getList() {
		return list;
	}

	public void setList(List<SystemDayTotalPayReward> list) {
		this.list = list;
	}
	
	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

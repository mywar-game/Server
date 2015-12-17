package com.adminTool.action;

import com.adminTool.service.ValentineRewardService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class AddValentineReward extends ALDAdminActionSupport{
	private static final long serialVersionUID = 6530892585296718235L;
	private String isCommit = "F";
	public Integer id;
	public Integer match_num;
	public String rewards;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMatch_num() {
		return match_num;
	}

	public void setMatch_num(Integer match_num) {
		this.match_num = match_num;
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
	public String execute(){
		if(isCommit.equals("F")){
		return INPUT;
		}
		else{
			ValentineRewardService service=ServiceCacheFactory
					.getServiceCache().getService(ValentineRewardService.class);
			Integer id= service.findMaxId();
			id++;
			service.add(id,match_num,rewards);
			return SUCCESS;
		}
		}
}

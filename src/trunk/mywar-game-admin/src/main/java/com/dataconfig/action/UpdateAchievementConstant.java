package com.dataconfig.action;

import com.dataconfig.bo.AAchievementConstant;
import com.dataconfig.service.AchievementConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateAchievementConstant extends ALDAdminActionSupport implements ModelDriven<AAchievementConstant> {

	private AAchievementConstant achievementConstant = new AAchievementConstant();
	
	private String isCommit = "F";
	
	public String execute() {
		AchievementConstantService achievementConstantService = ServiceCacheFactory.getServiceCache().getService(AchievementConstantService.class);
		if ("F".equals(this.isCommit)) {
			achievementConstant = achievementConstantService.findOneAchievementConstant(achievementConstant.getAchievementId());
			return INPUT;
		} else {
			achievementConstantService.updateOneAchievementConstant(achievementConstant);
			return SUCCESS;
		}
	}
	
	@Override
	public AAchievementConstant getModel() {
		return achievementConstant;
	}

	public void setAchievementConstant(AAchievementConstant achievementConstant) {
		this.achievementConstant = achievementConstant;
	}

	public AAchievementConstant getAchievementConstant() {
		return achievementConstant;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

}

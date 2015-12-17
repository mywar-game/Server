package com.dataconfig.action;

import com.dataconfig.bo.AAchievementConstant;
import com.dataconfig.service.AchievementConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddAchievementConstant extends ALDAdminActionSupport implements ModelDriven<AAchievementConstant> {

	private AAchievementConstant achievementConstant = new AAchievementConstant();
	
	private String isCommit = "F";

	
	public String execute() throws Exception {
		if ("F".equals(this.isCommit)) {
			return INPUT;
		} else {
			AchievementConstantService achievementConstantService = ServiceCacheFactory.getServiceCache().getService(AchievementConstantService.class);
			achievementConstantService.addAchievementConstant(achievementConstant);	
			return SUCCESS;
		}
	}
	
	@Override
	public AAchievementConstant getModel() {
		return getAchievementConstant();
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
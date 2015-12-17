package com.dataconfig.action;

import com.dataconfig.bo.AAchievementConstant;
import com.dataconfig.service.AchievementConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelAchievementConstant extends ALDAdminActionSupport implements ModelDriven<AAchievementConstant> {

	private AAchievementConstant achievementConstant = new AAchievementConstant();
	
	public void executeDel() {
		AchievementConstantService achievementConstantService = ServiceCacheFactory.getServiceCache().getService(AchievementConstantService.class);
		achievementConstantService.delAchievementConstant(achievementConstant.getAchievementId());
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

}

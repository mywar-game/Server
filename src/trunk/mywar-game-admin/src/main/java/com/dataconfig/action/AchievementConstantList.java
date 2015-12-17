package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.AAchievementConstant;
import com.dataconfig.service.AchievementConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class AchievementConstantList extends ALDAdminPageActionSupport {
	
	private static final long serialVersionUID = 1006150994598513567L;

	private List<AAchievementConstant> achievementConstantList;
	
	public String execute() {
		AchievementConstantService achievementConstantService = ServiceCacheFactory.getServiceCache().getService(AchievementConstantService.class);
		IPage<AAchievementConstant> pageList = achievementConstantService.findAchievementConstantList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (pageList != null) {
			achievementConstantList = (List<AAchievementConstant>) pageList.getData();
			super.setTotalPage(pageList.getTotalPage());
			super.setTotalSize(pageList.getTotalSize());
		}
		return SUCCESS;
	}

	public void setAchievementConstantList(List<AAchievementConstant> achievementConstantList) {
		this.achievementConstantList = achievementConstantList;
	}

	public List<AAchievementConstant> getAchievementConstantList() {
		return achievementConstantList;
	}
}
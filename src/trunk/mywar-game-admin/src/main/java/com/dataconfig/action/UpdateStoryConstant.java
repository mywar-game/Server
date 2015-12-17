package com.dataconfig.action;

import com.dataconfig.bo.MStoryConstant;
import com.dataconfig.service.StoryConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateStoryConstant extends ALDAdminActionSupport implements ModelDriven<MStoryConstant>{

	/** * */
	private static final long serialVersionUID = 8346036137563554334L;

	private MStoryConstant storyConstant = new MStoryConstant();
	
	private String isCommit = "F";
	
	public String execute() {
		StoryConstantService storyConstantService = ServiceCacheFactory.getServiceCache().getService(StoryConstantService.class);
		if ("F".equals(isCommit)) {
			storyConstant = storyConstantService.findOneStoryConstant(storyConstant.getStoryId());
			return INPUT;
		} else {
			storyConstantService.updateOneStoryConstant(storyConstant);
			return SUCCESS;
		}
	}
	
	@Override
	public MStoryConstant getModel() {
		return storyConstant;
	}

	public void setStoryConstant(MStoryConstant storyConstant) {
		this.storyConstant = storyConstant;
	}

	public MStoryConstant getStoryConstant() {
		return storyConstant;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

}

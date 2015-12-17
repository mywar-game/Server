package com.dataconfig.action;

import com.dataconfig.bo.MStoryConstant;
import com.dataconfig.service.StoryConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelStoryConstant extends ALDAdminActionSupport implements ModelDriven<MStoryConstant> {

	/** * */
	private static final long serialVersionUID = 1L;
	
	private MStoryConstant storyConstant = new MStoryConstant();
	
	public void executeDel() {
		StoryConstantService storyConstantService = ServiceCacheFactory.getServiceCache().getService(StoryConstantService.class);
		storyConstantService.delOneStoryConstant(storyConstant.getStoryId());
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

}

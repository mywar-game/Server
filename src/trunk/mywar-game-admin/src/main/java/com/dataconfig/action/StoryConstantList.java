package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.MStoryConstant;
import com.dataconfig.service.StoryConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class StoryConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = 6070679191838624944L;

	private List<MStoryConstant> storyConstantList;
	
	public String execute() {
		StoryConstantService storyConstantService = ServiceCacheFactory.getServiceCache().getService(StoryConstantService.class);
		IPage<MStoryConstant> list = storyConstantService.findStoryConstantList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			storyConstantList = (List<MStoryConstant>) list.getData();
			super.setTotalSize(list.getTotalSize());
			super.setTotalPage(list.getTotalPage());
		}
		return SUCCESS;
	}

	public void setStoryConstantList(List<MStoryConstant> storyConstantList) {
		this.storyConstantList = storyConstantList;
	}

	public List<MStoryConstant> getStoryConstantList() {
		return storyConstantList;
	}
}

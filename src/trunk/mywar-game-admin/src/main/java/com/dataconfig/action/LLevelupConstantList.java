package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.LLevelupConstant;
import com.dataconfig.service.LLevelupConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class LLevelupConstantList extends ALDAdminPageActionSupport {

	/** **/
	private static final long serialVersionUID = -1551391372276259823L;

	/** **/
	private List<LLevelupConstant> levelupConstantList;
	
	public String execute() {
		LLevelupConstantService levelupConstantService = ServiceCacheFactory.getServiceCache().getService(LLevelupConstantService.class);
		IPage<LLevelupConstant> list = levelupConstantService.findLLevelupConstantList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			levelupConstantList = (List<LLevelupConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public List<LLevelupConstant> getLevelupConstantList() {
		return levelupConstantList;
	}

	public void setLevelupConstantList(List<LLevelupConstant> levelupConstantList) {
		this.levelupConstantList = levelupConstantList;
	}
}

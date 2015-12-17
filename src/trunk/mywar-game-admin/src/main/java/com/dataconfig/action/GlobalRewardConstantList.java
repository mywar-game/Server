package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.GlobalRewardConstant;
import com.dataconfig.service.GlobalRewardConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class GlobalRewardConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = 1L;

	private List<GlobalRewardConstant> globalRewardConstantList;
	
	public String execute() {
		GlobalRewardConstantService globalRewardConstantService = ServiceCacheFactory.getServiceCache().getService(GlobalRewardConstantService.class);
		IPage<GlobalRewardConstant> list = globalRewardConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			globalRewardConstantList = (List<GlobalRewardConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	/**
	 * 获取 globalRewardConstantList 
	 */
	public List<GlobalRewardConstant> getGlobalRewardConstantList() {
		return globalRewardConstantList;
	}

	/**
	 * 设置 globalRewardConstantList 
	 */
	public void setGlobalRewardConstantList(
			List<GlobalRewardConstant> globalRewardConstantList) {
		this.globalRewardConstantList = globalRewardConstantList;
	}

}

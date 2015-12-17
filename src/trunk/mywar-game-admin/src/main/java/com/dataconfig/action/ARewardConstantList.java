package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.ARewardConstant;
import com.dataconfig.service.ARewardConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class ARewardConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = 542192945441616922L;

	private List<ARewardConstant> arewardConstantsList;
	
	public String execute() {
		ARewardConstantService aRewardConstantService = ServiceCacheFactory.getServiceCache().getService(ARewardConstantService.class);
		IPage<ARewardConstant> list = aRewardConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			arewardConstantsList = (List<ARewardConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	/**
	 * 获取 arewardConstantsList 
	 */
	public List<ARewardConstant> getArewardConstantsList() {
		return arewardConstantsList;
	}

	/**
	 * 设置 arewardConstantsList 
	 */
	public void setArewardConstantsList(List<ARewardConstant> arewardConstantsList) {
		this.arewardConstantsList = arewardConstantsList;
	}

}

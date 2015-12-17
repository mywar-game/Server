package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.DailyConstant;
import com.dataconfig.service.DailyConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class DailyConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = 2536159782086148458L;

	private List<DailyConstant> dailyConstantList;
	
	public String execute() {
		DailyConstantService dailyConstantService = ServiceCacheFactory.getServiceCache().getService(DailyConstantService.class);
		IPage<DailyConstant> list = dailyConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			dailyConstantList = (List<DailyConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	/**
	 * 获取 dailyConstantList 
	 */
	public List<DailyConstant> getDailyConstantList() {
		return dailyConstantList;
	}

	/**
	 * 设置 dailyConstantList 
	 */
	public void setDailyConstantList(List<DailyConstant> dailyConstantList) {
		this.dailyConstantList = dailyConstantList;
	}

}

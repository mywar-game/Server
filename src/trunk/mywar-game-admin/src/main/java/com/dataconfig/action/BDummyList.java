package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.BDummy;
import com.dataconfig.service.BDummyService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class BDummyList extends ALDAdminPageActionSupport {

	/** **/
	private static final long serialVersionUID = 7859306041092236780L;
	
	/** **/
	private List<BDummy> dummyList;

	public String execute() {
		BDummyService dummyService = ServiceCacheFactory.getServiceCache().getService(BDummyService.class);
		IPage<BDummy> list = dummyService.findBDummyList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			dummyList = (List<BDummy>) list.getData();
			super.setTotalSize(list.getTotalSize());
			super.setTotalPage(list.getTotalPage());
		}
		return SUCCESS;
	}
	
	public List<BDummy> getDummyList() {
		return dummyList;
	}

	public void setDummyList(List<BDummy> dummyList) {
		this.dummyList = dummyList;
	}
}

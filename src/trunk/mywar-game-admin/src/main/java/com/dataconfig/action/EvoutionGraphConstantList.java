package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.EvoutionGraphConstant;
import com.dataconfig.service.EvoutionGraphConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class EvoutionGraphConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = 3938890316590630580L;
	
	private List<EvoutionGraphConstant> evoutionGraphConstantList;
	
	public String execute() {
		EvoutionGraphConstantService evoutionGraphConstantService = ServiceCacheFactory.getServiceCache().getService(EvoutionGraphConstantService.class);
		IPage<EvoutionGraphConstant> list = evoutionGraphConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			evoutionGraphConstantList = (List<EvoutionGraphConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setEvoutionGraphConstantList(
			List<EvoutionGraphConstant> evoutionGraphConstantList) {
		this.evoutionGraphConstantList = evoutionGraphConstantList;
	}

	public List<EvoutionGraphConstant> getEvoutionGraphConstantList() {
		return evoutionGraphConstantList;
	}

}

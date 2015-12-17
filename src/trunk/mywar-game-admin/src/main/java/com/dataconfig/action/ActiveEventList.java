package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.ActiveEvent;
import com.dataconfig.service.ActiveEventService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class ActiveEventList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = 6351297788771769147L;
	
	private List<ActiveEvent> activeEventList;
	
	public String execute() {
		ActiveEventService activeEventService = ServiceCacheFactory.getServiceCache().getService(ActiveEventService.class);
		IPage<ActiveEvent> list = activeEventService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			activeEventList = (List<ActiveEvent>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setActiveEventList(List<ActiveEvent> activeEventList) {
		this.activeEventList = activeEventList;
	}

	public List<ActiveEvent> getActiveEventList() {
		return activeEventList;
	}

}

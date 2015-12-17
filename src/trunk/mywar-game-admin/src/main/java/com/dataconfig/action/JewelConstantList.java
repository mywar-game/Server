package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.JJewelConstant;
import com.dataconfig.service.JewelConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class JewelConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -5048053409804126445L;

	private List<JJewelConstant> jewelConstantList;
	
	public String execute() {
		JewelConstantService jewelConstantService = ServiceCacheFactory.getServiceCache().getService(JewelConstantService.class);
		IPage<JJewelConstant> list = jewelConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			jewelConstantList = (List<JJewelConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setJewelConstantList(List<JJewelConstant> jewelConstantList) {
		this.jewelConstantList = jewelConstantList;
	}

	public List<JJewelConstant> getJewelConstantList() {
		return jewelConstantList;
	}
	
}

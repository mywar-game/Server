package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.BMapConstant;
import com.dataconfig.service.MapConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class MapConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -699006818987049172L;
	
	private List<BMapConstant> mapConstantList;
	
	public String execute() {
		MapConstantService mapConstantService = ServiceCacheFactory.getServiceCache().getService(MapConstantService.class);
		IPage<BMapConstant> list = mapConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			mapConstantList = (List<BMapConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setMapConstantList(List<BMapConstant> mapConstantList) {
		this.mapConstantList = mapConstantList;
	}

	public List<BMapConstant> getMapConstantList() {
		return mapConstantList;
	}

}

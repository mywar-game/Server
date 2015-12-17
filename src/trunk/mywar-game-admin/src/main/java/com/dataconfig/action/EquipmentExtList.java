package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.EEquipmentExt;
import com.dataconfig.service.EquipmentExtService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class EquipmentExtList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -5324979138027305843L;
	
	private List<EEquipmentExt> equipmentExtList;
	
	public String execute() {
		EquipmentExtService equipmentExtService = ServiceCacheFactory.getServiceCache().getService(EquipmentExtService.class);
		IPage<EEquipmentExt> list = equipmentExtService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			equipmentExtList = (List<EEquipmentExt>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setEquipmentExtList(List<EEquipmentExt> equipmentExtList) {
		this.equipmentExtList = equipmentExtList;
	}

	public List<EEquipmentExt> getEquipmentExtList() {
		return equipmentExtList;
	}

}

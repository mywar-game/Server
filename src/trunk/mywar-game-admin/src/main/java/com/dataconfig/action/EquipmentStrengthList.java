package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.EEquipmentStrength;
import com.dataconfig.service.EquipmentStrengthService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class EquipmentStrengthList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = 5157645377602379958L;
	
	private List<EEquipmentStrength> equipmentStrengthList;
	
	public String execute() {
		EquipmentStrengthService equipmentStrengthService = ServiceCacheFactory.getServiceCache().getService(EquipmentStrengthService.class);
		IPage<EEquipmentStrength> list = equipmentStrengthService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			equipmentStrengthList = (List<EEquipmentStrength>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setEquipmentStrengthList(List<EEquipmentStrength> equipmentStrengthList) {
		this.equipmentStrengthList = equipmentStrengthList;
	}

	public List<EEquipmentStrength> getEquipmentStrengthList() {
		return equipmentStrengthList;
	}

}

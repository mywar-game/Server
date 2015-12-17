package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.PPetEquipment;
import com.dataconfig.service.PPetEquipmentService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class PPetEquipmentConstantList extends ALDAdminPageActionSupport {
	/**  */
	private static final long serialVersionUID = -3487201266547520885L;

	/**  */
	private List<PPetEquipment> petEquipmentList;
	
	@Override
	public String execute() throws Exception {
		PPetEquipmentService pPetEquipmentService = ServiceCacheFactory.getServiceCache().getService(PPetEquipmentService.class);
		petEquipmentList = pPetEquipmentService.findPPetEquipmentList();
		return SUCCESS;
	}

	/**
	 * @return the petEquipmentList
	 */
	public List<PPetEquipment> getPetEquipmentList() {
		return petEquipmentList;
	}

	/**
	 * @param petEquipmentList the petEquipmentList to set
	 */
	public void setPetEquipmentList(List<PPetEquipment> petEquipmentList) {
		this.petEquipmentList = petEquipmentList;
	}



}

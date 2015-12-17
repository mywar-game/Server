package com.dataconfig.action;

import com.dataconfig.bo.PPetEquipment;
import com.dataconfig.service.PPetEquipmentService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelPPetEquipment extends ALDAdminActionSupport implements ModelDriven<PPetEquipment> {

	/**  */
	private static final long serialVersionUID = 1L;

	/**  */
	private PPetEquipment pPetEquipment = new PPetEquipment();
	
	/**  */
	private String isCommit = "F";
	
	@Override
	public String execute() {
		PPetEquipmentService pPetEquipmentService = ServiceCacheFactory.getServiceCache().getService(PPetEquipmentService.class);
		if ("F".equals(this.isCommit)) {
			pPetEquipment = pPetEquipmentService.findOnePPetEquipment(pPetEquipment.getPetEquipmentId());
			return INPUT;
		} else {
			pPetEquipmentService.delPPetEquipment(pPetEquipment.getPetEquipmentId());
			return SUCCESS;
		}
	}
	
	/**
	 * @return the isCommit
	 */
	public String getIsCommit() {
		return isCommit;
	}

	/**
	 * @param isCommit the isCommit to set
	 */
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	@Override
	public PPetEquipment getModel() {
		return pPetEquipment;
	}

}

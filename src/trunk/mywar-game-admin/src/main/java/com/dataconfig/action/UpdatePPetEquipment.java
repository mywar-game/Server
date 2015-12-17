package com.dataconfig.action;

import com.dataconfig.bo.PPetEquipment;
import com.dataconfig.service.PPetEquipmentService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdatePPetEquipment extends ALDAdminActionSupport implements ModelDriven<PPetEquipment> {
	/**  */
	private static final long serialVersionUID = 1L;

	/**  */
	private  PPetEquipment petEquipment= new PPetEquipment();
	
	/**  */
	private String isCommit = "F";
	
	@Override
	public String execute() {
		PPetEquipmentService pPetEquipmentService = ServiceCacheFactory.getServiceCache().getService(PPetEquipmentService.class);
		if ("F".equals(this.isCommit)) {
			petEquipment = pPetEquipmentService.findOnePPetEquipment(petEquipment.getPetEquipmentId());
			return INPUT;
		} else {
			pPetEquipmentService.updatePPetEquipment(petEquipment.getPetEquipmentId());
			return SUCCESS;
		}
	}
	
	@Override
	public PPetEquipment getModel() {
		return petEquipment;
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
	
	/**
	 * @return the petEquipment
	 */
	public PPetEquipment getPetEquipment() {
		return petEquipment;
	}

	/**
	 * @param petEquipment the petEquipment to set
	 */
	public void setPetEquipment(PPetEquipment petEquipment) {
		this.petEquipment = petEquipment;
	}
}

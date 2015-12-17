package com.dataconfig.action;

import com.dataconfig.bo.PPetEquipment;
import com.dataconfig.service.PPetEquipmentService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddPPetExtraConstant extends ALDAdminActionSupport implements ModelDriven<PPetEquipment> {
	
	/**  */
	private static final long serialVersionUID = 1L;
	
	/**  */
	private PPetEquipment pPetEquipment = new PPetEquipment();
	
	/**  */
	private String isCommit = "F";
	
	@Override
	public String execute() throws Exception {
		if ("F".equals(this.isCommit)) {
			return INPUT;
		} else {
			PPetEquipmentService pPetEquipmentService = ServiceCacheFactory.getServiceCache().getService(PPetEquipmentService.class);
			if (pPetEquipmentService.findOnePPetEquipment(pPetEquipment.getPetEquipmentId()) != null) {
				super.setErroDescrip("宠物装备加成不存在！");
				return INPUT;
			}
			pPetEquipmentService.addPPetEquipment(pPetEquipment);
			return SUCCESS;
		}
	}
	
	@Override
	public PPetEquipment getModel() {
		return pPetEquipment;
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
	 * @return the pPetEquipment
	 */
	public PPetEquipment getpPetEquipment() {
		return pPetEquipment;
	}

	/**
	 * @param pPetEquipment the pPetEquipment to set
	 */
	public void setpPetEquipment(PPetEquipment pPetEquipment) {
		this.pPetEquipment = pPetEquipment;
	}
	
}

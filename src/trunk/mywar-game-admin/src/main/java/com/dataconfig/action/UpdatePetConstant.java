package com.dataconfig.action;

import com.dataconfig.bo.PPetConstant;
import com.dataconfig.service.PetConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdatePetConstant extends ALDAdminActionSupport implements ModelDriven<PPetConstant> {

	/**  */
	private static final long serialVersionUID = 1L;

	/**  */
	private PPetConstant petConstant = new PPetConstant();
	
	/**  */
	private String isCommit = "F";
	
	@Override
	public String execute() {
		PetConstantService petConstantService = ServiceCacheFactory.getServiceCache().getService(PetConstantService.class);
		if ("F".equals(this.isCommit)) {
			petConstant = petConstantService.findOnePetConstant(petConstant.getPetId());
			return INPUT;
		} else {
			petConstantService.updateOnePetConstant(petConstant);
			return SUCCESS;
		}
	}
	
	@Override
	public PPetConstant getModel() {
		return petConstant;
	}

	/**
	 * @return the petConstant
	 */
	public PPetConstant getPetConstant() {
		return petConstant;
	}

	/**
	 * @param petConstant the petConstant to set
	 */
	public void setPetConstant(PPetConstant petConstant) {
		this.petConstant = petConstant;
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
}

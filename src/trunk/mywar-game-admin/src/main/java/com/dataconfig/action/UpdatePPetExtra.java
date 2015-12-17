package com.dataconfig.action;

import com.dataconfig.bo.PPetExtra;
import com.dataconfig.service.PPetExtraService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdatePPetExtra extends ALDAdminActionSupport implements ModelDriven<PPetExtra> {
	/**  */
	private static final long serialVersionUID = 1L;

	/**  */
	private PPetExtra petExtra = new PPetExtra();
	
	/**  */
	private String isCommit = "F";
	
	@Override
	public String execute() {
		PPetExtraService pPetExtraService = ServiceCacheFactory.getServiceCache().getService(PPetExtraService.class);
		if ("F".equals(this.isCommit)) {
			petExtra = pPetExtraService.findOnePPetExtra(petExtra.getType());
			return INPUT;
		} else {
			pPetExtraService.updateOnePetExtra(petExtra);
			return SUCCESS;
		}
	}
	
	@Override
	public PPetExtra getModel() {
		return petExtra;
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
	 * @return the petExtra
	 */
	public PPetExtra getPetExtra() {
		return petExtra;
	}

	/**
	 * @param petExtra the petExtra to set
	 */
	public void setPetExtra(PPetExtra petExtra) {
		this.petExtra = petExtra;
	}
	
	
}

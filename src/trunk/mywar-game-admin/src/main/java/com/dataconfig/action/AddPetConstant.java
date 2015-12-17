package com.dataconfig.action;

import com.dataconfig.bo.PPetConstant;
import com.dataconfig.service.PetConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddPetConstant extends ALDAdminActionSupport implements ModelDriven<PPetConstant> {

	/**  */
	private static final long serialVersionUID = 1L;
	
	/**  */
	private PPetConstant petConstant = new PPetConstant();
	
	/**  */
	private String isCommit = "F";
	
	@Override
	public String execute() throws Exception {
		if ("F".equals(this.isCommit)) {
			return INPUT;
		} else {
			PetConstantService petConstantService = ServiceCacheFactory.getServiceCache().getService(PetConstantService.class);
			if (petConstantService.findOnePetConstant(petConstant.getPetId()) != null) {
				super.setErroDescrip("宠物编号已存在！");
				return INPUT;
			}
			petConstantService.addPetConstant(petConstant);
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

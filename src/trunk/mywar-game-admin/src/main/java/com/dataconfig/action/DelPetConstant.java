package com.dataconfig.action;

import com.dataconfig.bo.PPetConstant;
import com.dataconfig.service.PetConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelPetConstant extends ALDAdminActionSupport implements ModelDriven<PPetConstant> {


	/**  */
	private static final long serialVersionUID = -4744258105430557203L;

	/**  */
	private PPetConstant petConstant = new PPetConstant();
	
	public void executeDel() throws Exception {
		PetConstantService petConstantService = ServiceCacheFactory.getServiceCache().getService(PetConstantService.class);
		petConstantService.delPetConstant(petConstant.getPetId());
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

}

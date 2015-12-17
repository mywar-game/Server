package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.PPetConstant;
import com.dataconfig.service.PetConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class PetConstantList extends ALDAdminPageActionSupport {

	/**  */
	private static final long serialVersionUID = -3487201266547520885L;

	/**  */
	private List<PPetConstant> petConstantList;
	
	@Override
	public String execute() throws Exception {
		PetConstantService petConstantService = ServiceCacheFactory.getServiceCache().getService(PetConstantService.class);
		petConstantList = petConstantService.findPetConstantList();
		return SUCCESS;
	}

	public void setPetConstantList(List<PPetConstant> petConstantList) {
		this.petConstantList = petConstantList;
	}

	public List<PPetConstant> getPetConstantList() {
		return petConstantList;
	}
	
}

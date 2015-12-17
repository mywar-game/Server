package com.dataconfig.action;

import java.util.List;
import com.dataconfig.bo.PPetExtra;
import com.dataconfig.service.PPetExtraService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class PPetExtraConstantList extends ALDAdminPageActionSupport {
	/**  */
	private static final long serialVersionUID = -3487201266547520885L;

	/**  */
	private List<PPetExtra> petExtraList;
	
	@Override
	public String execute() throws Exception {
		PPetExtraService pPetExtraService = ServiceCacheFactory.getServiceCache().getService(PPetExtraService.class);
		petExtraList = pPetExtraService.findPPetExtraList();
		return SUCCESS;
	}

	/**
	 * @return the petExtraList
	 */
	public List<PPetExtra> getPetExtraList() {
		return petExtraList;
	}

	/**
	 * @param petExtraList the petExtraList to set
	 */
	public void setPetExtraList(List<PPetExtra> petExtraList) {
		this.petExtraList = petExtraList;
	}

	
	
}

package com.dataconfig.action;

import com.dataconfig.bo.PPetExtra;
import com.dataconfig.service.PPetExtraService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelPPetExtra extends ALDAdminActionSupport implements ModelDriven<PPetExtra> {

	/**  */
	private static final long serialVersionUID = 1L;

	/**  */
	private PPetExtra pPetExtra = new PPetExtra();
	
	/**  */
	private String isCommit = "F";
	
	@Override
	public String execute() {
		PPetExtraService pPetExtraService = ServiceCacheFactory.getServiceCache().getService(PPetExtraService.class);
		if ("F".equals(this.isCommit)) {
			pPetExtra = pPetExtraService.findOnePPetExtra(pPetExtra.getType());
			return INPUT;
		} else {
			pPetExtraService.delPPetExtra(pPetExtra.getType());
			return SUCCESS;
		}
	}
	
	@Override
	public PPetExtra getModel() {
		return pPetExtra;
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

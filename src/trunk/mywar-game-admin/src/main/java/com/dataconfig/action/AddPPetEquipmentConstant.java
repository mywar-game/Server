package com.dataconfig.action;

import com.dataconfig.bo.PPetExtra;
import com.dataconfig.service.PPetExtraService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddPPetEquipmentConstant extends ALDAdminActionSupport implements ModelDriven<PPetExtra> {
	
	/**  */
	private static final long serialVersionUID = 1L;
	
	/**  */
	private PPetExtra pPetExtra = new PPetExtra();
	
	/**  */
	private String isCommit = "F";
	
	@Override
	public String execute() throws Exception {
		if ("F".equals(this.isCommit)) {
			return INPUT;
		} else {
			PPetExtraService pPetExtraService = ServiceCacheFactory.getServiceCache().getService(PPetExtraService.class);
			if (pPetExtraService.findOnePPetExtra(pPetExtra.getType()) != null) {
				super.setErroDescrip("宠物额外加成不存在！");
				return INPUT;
			}
			pPetExtraService.addPPetExtra(pPetExtra);
			return SUCCESS;
		}
	}
	
	@Override
	public PPetExtra getModel() {
		return pPetExtra;
	}

	/**
	 * @return the pPetExtra
	 */
	public PPetExtra getpPetExtra() {
		return pPetExtra;
	}

	/**
	 * @param pPetExtra the pPetExtra to set
	 */
	public void setpPetExtra(PPetExtra pPetExtra) {
		this.pPetExtra = pPetExtra;
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

package com.dataconfig.action;

import com.dataconfig.bo.SEffectConstant;
import com.dataconfig.service.SEffectConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateSEffectConstant extends ALDAdminActionSupport implements ModelDriven<SEffectConstant> {
	
	private static final long serialVersionUID = -7799717754451347370L;

	private SEffectConstant seffectConstant = new SEffectConstant();
	
	private String isCommit = "F";
	
	public String execute() {
		SEffectConstantService sEffectConstantService = ServiceCacheFactory.getServiceCache().getService(SEffectConstantService.class);
		if ("F".equals(this.isCommit)) {
			seffectConstant = sEffectConstantService.findOneSEffectConstant(seffectConstant.getEffectId());
			return INPUT;
		} else {
			sEffectConstantService.updateOneSEffectConstant(seffectConstant);
			return SUCCESS;
		}
	}

	public SEffectConstant getSeffectConstant() {
		return seffectConstant;
	}

	public void setSeffectConstant(SEffectConstant seffectConstant) {
		this.seffectConstant = seffectConstant;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	@Override
	public SEffectConstant getModel() {
		return seffectConstant;
	}
}

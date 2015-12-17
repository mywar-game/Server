package com.dataconfig.action;

import com.dataconfig.bo.HHeroConstant;
import com.dataconfig.service.HHeroConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateHHeroConstant  extends ALDAdminActionSupport implements ModelDriven<HHeroConstant> {

	private static final long serialVersionUID = -5781003037516644708L;
	
	private HHeroConstant hheroConstant = new HHeroConstant();

	private String isCommit = "F";
	
	public String execute() {
		HHeroConstantService hHeroConstantService = ServiceCacheFactory.getServiceCache().getService(HHeroConstantService.class);
		if ("F".equals(this.isCommit)) {
			hheroConstant = hHeroConstantService.findOneHHeroConstant(hheroConstant.getHeroId());
			return INPUT;
		} else {
			hHeroConstantService.updateOneHHeroConstant(hheroConstant);
			return SUCCESS;
		}
	}
	
	public HHeroConstant getModel() {
		return hheroConstant;
	}
	
	public HHeroConstant getHheroConstant() {
		return hheroConstant;
	}
	
	public void setHheroConstant(HHeroConstant hheroConstant) {
		this.hheroConstant = hheroConstant;
	}
	
	public String getIsCommit() {
		return isCommit;
	}
	
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
}

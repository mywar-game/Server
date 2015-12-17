package com.dataconfig.action;

import com.dataconfig.bo.HHeroConstant;
import com.dataconfig.service.HHeroConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddHHeroConstant extends  ALDAdminActionSupport implements ModelDriven<HHeroConstant> {

	private static final long serialVersionUID = 6392570960781084709L;

	private HHeroConstant hheroConstant = new HHeroConstant();
	
	private String isCommit = "F";

	public HHeroConstant getModel() {
		return hheroConstant;
	}
	
	public String execute() throws Exception {
		if ("F".equals(this.isCommit)) {
			return INPUT;
		} else {
			HHeroConstantService hHeroConstantService = ServiceCacheFactory.getServiceCache().getService(HHeroConstantService.class);
			hHeroConstantService.addHHeroConstant(hheroConstant);
			return "ok";
		}
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

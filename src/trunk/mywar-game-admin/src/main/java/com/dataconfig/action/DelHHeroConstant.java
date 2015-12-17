package com.dataconfig.action;

import com.dataconfig.bo.HHeroConstant;
import com.dataconfig.service.HHeroConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelHHeroConstant  extends ALDAdminActionSupport implements ModelDriven<HHeroConstant> {
	
	private static final long serialVersionUID = 39598065923622481L;
	
	private HHeroConstant hheroConstant = new HHeroConstant();
	
	public void executeDel() {
		HHeroConstantService mMissionConstantService = ServiceCacheFactory.getServiceCache().getService(HHeroConstantService.class);
		mMissionConstantService.delHHeroConstant(hheroConstant.getHeroId());
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
}

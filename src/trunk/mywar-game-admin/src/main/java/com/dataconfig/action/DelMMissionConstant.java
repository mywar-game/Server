package com.dataconfig.action;

import com.dataconfig.bo.MMissionConstant;
import com.dataconfig.service.MMissionConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelMMissionConstant extends ALDAdminActionSupport implements ModelDriven<MMissionConstant> {

	private static final long serialVersionUID = 4680916534082212143L;
	
	private MMissionConstant mmissionConstant = new MMissionConstant();
	
	public void executeDel() {
		MMissionConstantService mMissionConstantService = ServiceCacheFactory.getServiceCache().getService(MMissionConstantService.class);
		mMissionConstantService.delMMissionConstant(mmissionConstant.getMissionId());
	}
	
	public MMissionConstant getModel() {
		return mmissionConstant;
	}

	public MMissionConstant getMmissionConstant() {
		return mmissionConstant;
	}

	public void setMmissionConstant(MMissionConstant mmissionConstant) {
		this.mmissionConstant = mmissionConstant;
	}

}

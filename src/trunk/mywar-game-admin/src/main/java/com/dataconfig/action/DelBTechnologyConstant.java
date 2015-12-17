package com.dataconfig.action;

import com.dataconfig.bo.BTechnologyConstant;
import com.dataconfig.service.BTechnologyConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelBTechnologyConstant extends ALDAdminActionSupport implements ModelDriven<BTechnologyConstant> {

	/** **/
	private static final long serialVersionUID = 6563336104178247778L;
	
	/** **/
	private BTechnologyConstant technologyConstant = new BTechnologyConstant();
	
	public void executeDel() {
		BTechnologyConstantService technologyConstantService = ServiceCacheFactory.getServiceCache().getService(BTechnologyConstantService.class);
		technologyConstantService.delBTechnologyConstant(technologyConstant.getTechnologyConstantId());
	}

	@Override
	public BTechnologyConstant getModel() {
		// TODO Auto-generated method stub
		return technologyConstant;
	}

	public BTechnologyConstant getTechnologyConstant() {
		return technologyConstant;
	}

	public void setTechnologyConstant(BTechnologyConstant technologyConstant) {
		this.technologyConstant = technologyConstant;
	}
}

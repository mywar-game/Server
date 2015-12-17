package com.dataconfig.action;

import com.dataconfig.bo.BNpcConstant;
import com.dataconfig.service.BNpcConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelBNpcConstant extends ALDAdminActionSupport implements ModelDriven<BNpcConstant> {

	/** **/
	private static final long serialVersionUID = 8457903277355179394L;

	/** **/
	private BNpcConstant npcConstant = new BNpcConstant();
	
	public void executeDel() {
		BNpcConstantService npcConstantService = ServiceCacheFactory.getServiceCache().getService(BNpcConstantService.class);
		npcConstantService.delBNpcConstant(npcConstant.getNpcId());
	}
	
	@Override
	public BNpcConstant getModel() {
		// TODO Auto-generated method stub
		return npcConstant;
	}

	public BNpcConstant getNpcConstant() {
		return npcConstant;
	}

	public void setNpcConstant(BNpcConstant npcConstant) {
		this.npcConstant = npcConstant;
	}
}

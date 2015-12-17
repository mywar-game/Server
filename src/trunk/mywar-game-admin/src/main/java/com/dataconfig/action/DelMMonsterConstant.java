package com.dataconfig.action;

import com.dataconfig.bo.MMonsterConstant;
import com.dataconfig.service.MMonsterConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelMMonsterConstant extends ALDAdminActionSupport implements ModelDriven<MMonsterConstant> {

	private static final long serialVersionUID = 2862059405258015166L;

	private MMonsterConstant monsterConstant = new MMonsterConstant();;
	
	public void executeDel() {
		MMonsterConstantService monsterConstantService = ServiceCacheFactory.getServiceCache().getService(MMonsterConstantService.class);
		monsterConstantService.delOneMMonsterConstant(monsterConstant.getMonsterId());
	}
	
	@Override
	public MMonsterConstant getModel() {
		return monsterConstant;
	}

	public void setMonsterConstant(MMonsterConstant monsterConstant) {
		this.monsterConstant = monsterConstant;
	}

	public MMonsterConstant getMonsterConstant() {
		return monsterConstant;
	}

}

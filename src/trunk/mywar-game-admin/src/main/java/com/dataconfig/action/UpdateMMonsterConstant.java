package com.dataconfig.action;

import com.dataconfig.bo.MMonsterConstant;
import com.dataconfig.service.MMonsterConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateMMonsterConstant extends ALDAdminActionSupport implements ModelDriven<MMonsterConstant> {

	private static final long serialVersionUID = 4829175290424727155L;

	private MMonsterConstant monsterConstant = new MMonsterConstant();
	
	private String isCommit = "F";
	
	public String execute() {
		MMonsterConstantService monsterConstantService = ServiceCacheFactory.getServiceCache().getService(MMonsterConstantService.class);
		if ("F".equals(this.isCommit)) {
			monsterConstant = monsterConstantService.findOneMMonsterConstant(monsterConstant.getMonsterId());
			return INPUT;
		} else {
			monsterConstantService.updateOneMMonsterConstant(monsterConstant);
			return SUCCESS;
		}
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

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

}

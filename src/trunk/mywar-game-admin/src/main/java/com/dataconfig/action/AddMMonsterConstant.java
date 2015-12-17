package com.dataconfig.action;

import com.dataconfig.bo.MMonsterConstant;
import com.dataconfig.service.MMonsterConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddMMonsterConstant extends ALDAdminActionSupport implements ModelDriven<MMonsterConstant> {

	private static final long serialVersionUID = -4502355961302639569L;

	private MMonsterConstant monsterConstant = new MMonsterConstant();;
	
	private String isCommit = "F";
	
	public String execute() {
		if ("F".equals(this.isCommit)) {
			return INPUT;
		} else {
			MMonsterConstantService monsterConstantService = ServiceCacheFactory.getServiceCache().getService(MMonsterConstantService.class);
			if (monsterConstantService.findOneMMonsterConstant(monsterConstant.getMonsterId()) != null) {
				super.setErroDescrip("此编号已存在！");
				return ERROR;
			}
			monsterConstantService.addOneMMonsterConstant(monsterConstant);
			return SUCCESS;
		}
	}
	
	@Override
	public MMonsterConstant getModel() {
		// TODO Auto-generated method stub
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

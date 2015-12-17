package com.dataconfig.action;

import com.dataconfig.bo.SSkillConstant;
import com.dataconfig.service.SSkillConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelSSkillConstant extends ALDAdminActionSupport implements ModelDriven<SSkillConstant> {
	
	private static final long serialVersionUID = 6527490818803114358L;
	
	private SSkillConstant sskillConstant = new SSkillConstant();

	public void executeDel() {
		SSkillConstantService sSkillConstantService = ServiceCacheFactory.getServiceCache().getService(SSkillConstantService.class);
		sSkillConstantService.delOneSSkillConstant(sskillConstant.getSkillConstantId());
	}
	
	@Override
	public SSkillConstant getModel() {
		return sskillConstant;
	}
	
	public SSkillConstant getSskillConstant() {
		return sskillConstant;
	}

	public void setSskillConstant(SSkillConstant sskillConstant) {
		this.sskillConstant = sskillConstant;
	}
}

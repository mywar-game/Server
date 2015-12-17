package com.dataconfig.action;

import java.util.ArrayList;
import java.util.List;

import com.dataconfig.bo.SEffectConstant;
import com.dataconfig.bo.SSkillConstant;
import com.dataconfig.service.SEffectConstantService;
import com.dataconfig.service.SSkillConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddSSkillConstant extends ALDAdminActionSupport implements ModelDriven<SSkillConstant> {

	private static final long serialVersionUID = -232385998343818687L;

	private SSkillConstant sskillConstant = new SSkillConstant();
	
	private List<SEffectConstant> seffectConstantList = new ArrayList<SEffectConstant>();
	
	private String isCommit = "F";
	
	public String execute() {
		SEffectConstantService sEffectConstantService = ServiceCacheFactory.getServiceCache().getService(SEffectConstantService.class);
		if ("F".equals(this.isCommit)) {
			seffectConstantList = sEffectConstantService.findSEffectConstantList();
			return INPUT;
		} else {
			SSkillConstantService sSkillConstantService = ServiceCacheFactory.getServiceCache().getService(SSkillConstantService.class);
			sSkillConstantService.addOneSSkillConstant(sskillConstant);
			return "ok";
		}
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

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public void setSeffectConstantList(List<SEffectConstant> seffectConstantList) {
		this.seffectConstantList = seffectConstantList;
	}

	public List<SEffectConstant> getSeffectConstantList() {
		return seffectConstantList;
	}
}

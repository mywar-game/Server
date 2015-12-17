package com.dataconfig.action;

import com.dataconfig.bo.LLevelupConstant;
import com.dataconfig.service.LLevelupConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelLLevelupConstant extends ALDAdminActionSupport implements ModelDriven<LLevelupConstant> {

	/** **/
	private static final long serialVersionUID = 993736660713270972L;

	/** **/
	private LLevelupConstant levelupConstant = new LLevelupConstant();
	
	public void executeDel() {
		LLevelupConstantService levelupConstantService = ServiceCacheFactory.getServiceCache().getService(LLevelupConstantService.class);
		levelupConstantService.delLLevelupConstant(levelupConstant.getLevel());
	}
	
	@Override
	public LLevelupConstant getModel() {
		// TODO Auto-generated method stub
		return levelupConstant;
	}

	public LLevelupConstant getLevelupConstant() {
		return levelupConstant;
	}

	public void setLevelupConstant(LLevelupConstant levelupConstant) {
		this.levelupConstant = levelupConstant;
	}
}

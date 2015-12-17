package com.dataconfig.action;

import com.dataconfig.bo.LLevelupConstant;
import com.dataconfig.service.LLevelupConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateLLevelupConstant extends ALDAdminActionSupport implements ModelDriven<LLevelupConstant> {

	private static final long serialVersionUID = 7266967110831794730L;
	
	/** **/
	private LLevelupConstant levelupConstant = new LLevelupConstant();

	/** **/
	private String isCommit = "F";
	
	public String execute() {
		LLevelupConstantService levelupConstantService = ServiceCacheFactory.getServiceCache().getService(LLevelupConstantService.class);
		if ("F".equals(isCommit)) {
			levelupConstant = levelupConstantService.findOneLLevelupConstant(levelupConstant.getLevel());
			return INPUT;
		} else {
			levelupConstantService.updateOneLLevelupConstant(levelupConstant);
			return SUCCESS;
		}
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

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
}

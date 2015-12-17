package com.dataconfig.action;

import com.dataconfig.bo.BTechnologyConstant;
import com.dataconfig.service.BTechnologyConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateBTechnologyConstant extends ALDAdminActionSupport implements ModelDriven<BTechnologyConstant> {

	/** **/
	private static final long serialVersionUID = -5969749042363802294L;
	
	/** **/
	private BTechnologyConstant technologyConstant = new BTechnologyConstant();
	
	/** **/
	private String isCommit = "F";
	
	public String execute() {
		BTechnologyConstantService technologyConstantService = ServiceCacheFactory.getServiceCache().getService(BTechnologyConstantService.class);
		if ("F".equals(isCommit)) {
			technologyConstant = technologyConstantService.findOneBTechnologyConstant(technologyConstant.getTechnologyConstantId());
			return INPUT;
		} else {
			technologyConstantService.updateOneBTechnologyConstant(technologyConstant);
			return SUCCESS;
		}
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

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
}

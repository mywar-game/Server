package com.dataconfig.action;

import com.dataconfig.bo.TTreasureConstant;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddTTreasureConstant extends ALDAdminActionSupport implements ModelDriven<TTreasureConstant> {

	
	/** **/
	private static final long serialVersionUID = 1950859203261928862L;

	/** **/
	private TTreasureConstant treasureConstant = new TTreasureConstant();
	
	/** **/
	private String isCommit = "F";
	
	public String execute() {
		if ("F".equals(isCommit)) {
			return INPUT;
		} else {
			TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
			treasureConstantService.addTTreasureConstant(treasureConstant);
			return SUCCESS;
		}
	}

	@Override
	public TTreasureConstant getModel() {
		// TODO Auto-generated method stub
		return treasureConstant;
	}

	public TTreasureConstant getTreasureConstant() {
		return treasureConstant;
	}

	public void setTreasureConstant(TTreasureConstant treasureConstant) {
		this.treasureConstant = treasureConstant;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
}

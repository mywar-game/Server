package com.dataconfig.action;

import com.dataconfig.bo.TTreasureConstant;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateTTreasureConstant extends ALDAdminActionSupport implements ModelDriven<TTreasureConstant> {

	/**  **/
	private static final long serialVersionUID = -3964214878613683462L;

	/**  **/
	private TTreasureConstant treasureConstant = new TTreasureConstant();
	
	/**  **/
	private String isCommit = "F";
	
	public String execute() {
		TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		if ("F".equals(isCommit)) {
			treasureConstant = treasureConstantService.findOneTTreasureConstant(treasureConstant.getTreasureId());
			return INPUT;
		} else {
			treasureConstant.setPathWay(treasureConstant.getPathWay().replaceAll(" ", ""));
			treasureConstantService.updateOneTTreasureConstant(treasureConstant);
			return SUCCESS;
		}
	}

	@Override
	public TTreasureConstant getModel() {
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

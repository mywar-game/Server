package com.dataconfig.action;

import com.dataconfig.bo.BBuildingConstant;
import com.dataconfig.service.BBuildingConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateBBuildingConstant extends ALDAdminActionSupport implements ModelDriven<BBuildingConstant> {

	/** **/
	private static final long serialVersionUID = 8316976488114806406L;

	/** **/
	private BBuildingConstant buildingConstant = new BBuildingConstant();
	
	/** **/
	private String isCommit = "F";
	
	public String execute() {
		BBuildingConstantService buildingConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingConstantService.class);
		if ("F".equals(isCommit)) {
			buildingConstant = buildingConstantService.findOneBBuildingConstant(buildingConstant.getBuildingId());
			return INPUT;
		} else {
			buildingConstantService.updateOneBBuildingConstant(buildingConstant);
			return SUCCESS;
		}
	}
	
	@Override
	public BBuildingConstant getModel() {
		// TODO Auto-generated method stub
		return buildingConstant;
	}

	public BBuildingConstant getBuildingConstant() {
		return buildingConstant;
	}

	public void setBuildingConstant(BBuildingConstant buildingConstant) {
		this.buildingConstant = buildingConstant;
	}
}

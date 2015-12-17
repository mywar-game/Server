package com.dataconfig.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dataconfig.bo.BBuildingconstraintConstant;
import com.dataconfig.service.BBuildingConstantService;
import com.dataconfig.service.BBuildingconstraintConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateBBuildingconstraintConstant extends ALDAdminActionSupport implements ModelDriven<BBuildingconstraintConstant> {

	private static final long serialVersionUID = -2343649343402447289L;
	
	private BBuildingconstraintConstant buildingconstraintConstant = new BBuildingconstraintConstant();
	
	private Map<Integer, String> buildingIDNameMap = new LinkedHashMap<Integer, String>();
	
	private String isCommit = "F";
	
	public String execute() {
		BBuildingConstantService buildingConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingConstantService.class);
		buildingIDNameMap = buildingConstantService.findBuildingIDNameMap();
		
		BBuildingconstraintConstantService buildingconstraintConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingconstraintConstantService.class);
		if ("F".equals(isCommit)) {
			buildingconstraintConstant = buildingconstraintConstantService.findOneBBuildingconstraintConstant(buildingconstraintConstant.getBuildingConstraintId());
			return INPUT;
		} else {
			buildingconstraintConstantService.updateOneBBuildingconstraintConstant(buildingconstraintConstant);
			return SUCCESS;
		}
	}
	
	@Override
	public BBuildingconstraintConstant getModel() {
		// TODO Auto-generated method stub
		return buildingconstraintConstant;
	}

	public BBuildingconstraintConstant getBuildingconstraintConstant() {
		return buildingconstraintConstant;
	}

	public void setBuildingconstraintConstant(
			BBuildingconstraintConstant buildingconstraintConstant) {
		this.buildingconstraintConstant = buildingconstraintConstant;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public void setBuildingIDNameMap(Map<Integer, String> buildingIDNameMap) {
		this.buildingIDNameMap = buildingIDNameMap;
	}

	public Map<Integer, String> getBuildingIDNameMap() {
		return buildingIDNameMap;
	}
}

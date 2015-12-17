package com.dataconfig.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dataconfig.bo.BBuildingconstraintConstant;
import com.dataconfig.service.BBuildingConstantService;
import com.dataconfig.service.BBuildingconstraintConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddBBuildingconstraintConstant extends ALDAdminPageActionSupport implements ModelDriven<BBuildingconstraintConstant> {

	private static final long serialVersionUID = -8566889307967179014L;

	private BBuildingconstraintConstant buildingconstraintConstant = new BBuildingconstraintConstant();
	
	private Map<Integer, String> buildingIDNameMap = new LinkedHashMap<Integer, String>();
	
	private String isCommit = "F";
	
	public String execute() {
		BBuildingConstantService buildingConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingConstantService.class);
		buildingIDNameMap = buildingConstantService.findBuildingIDNameMap();
		
		if ("F".equals(isCommit)) {
			return INPUT;
		} else {
			BBuildingconstraintConstantService buildingconstraintConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingconstraintConstantService.class);
			buildingconstraintConstantService.addBBuildingconstraintConstant(buildingconstraintConstant);
			return SUCCESS;
		}
	}
	
	@Override
	public BBuildingconstraintConstant getModel() {
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

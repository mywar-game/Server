package com.dataconfig.action;

import java.util.Map;

import com.dataconfig.bo.BNpcConstant;
import com.dataconfig.service.BBuildingConstantService;
import com.dataconfig.service.BNpcConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddBNpcConstant extends ALDAdminActionSupport implements ModelDriven<BNpcConstant> {

	private static final long serialVersionUID = 3488238317681112806L;

	private BNpcConstant npcConstant = new BNpcConstant();
	
	private Map<Integer, String> buildingIDNameMap;
	
	private String isCommit = "F";
	
	public String execute() {
		BBuildingConstantService buildingConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingConstantService.class);
		buildingIDNameMap = buildingConstantService.findBuildingIDNameMap();
		
		if ("F".equals(isCommit)) {
			return INPUT;
		} else {
			BNpcConstantService npcConstantService = ServiceCacheFactory.getServiceCache().getService(BNpcConstantService.class);
			npcConstantService.addBNpcConstant(npcConstant);
			return SUCCESS;
		}
	}
	
	@Override
	public BNpcConstant getModel() {
		// TODO Auto-generated method stub
		return npcConstant;
	}

	public BNpcConstant getNpcConstant() {
		return npcConstant;
	}

	public void setNpcConstant(BNpcConstant npcConstant) {
		this.npcConstant = npcConstant;
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

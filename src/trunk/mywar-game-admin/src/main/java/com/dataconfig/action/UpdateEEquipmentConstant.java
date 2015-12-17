package com.dataconfig.action;

import java.util.Arrays;
import java.util.Map;

import com.dataconfig.bo.EEquipmentConstant;
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.HHeroConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateEEquipmentConstant extends ALDAdminActionSupport implements ModelDriven<EEquipmentConstant> {

	private static final long serialVersionUID = 8919544457335994124L;

	private EEquipmentConstant eequipmentConstant = new EEquipmentConstant();
	
	private Map<Integer, String> heroIdNameMap;
	
	private String isCommit = "F";
	
	public String execute() {
		EEquipmentConstantService eEquipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
		if ("F".equals(this.isCommit)) {
			HHeroConstantService heroConstantService = ServiceCacheFactory.getServiceCache().getService(HHeroConstantService.class);
			heroIdNameMap = heroConstantService.findHeroIdNameMap();
			
			eequipmentConstant = eEquipmentConstantService.findOneEEquipmentConstant(eequipmentConstant.getEquipmentId());
			return INPUT;
		} else {
			eequipmentConstant.setPathWay(eequipmentConstant.getPathWay().replaceAll(" ", ""));
//			System.out.println(eequipmentConstant.getEquipmentId());
			eEquipmentConstantService.updateOneEEquipmentConstant(eequipmentConstant);
			return SUCCESS;
		}
	}
	
	@Override
	public EEquipmentConstant getModel() {
		return eequipmentConstant;
	}

	public EEquipmentConstant getEequipmentConstant() {
		return eequipmentConstant;
	}

	public void setEequipmentConstant(EEquipmentConstant eequipmentConstant) {
		this.eequipmentConstant = eequipmentConstant;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public void setHeroIdNameMap(Map<Integer, String> heroIdNameMap) {
		this.heroIdNameMap = heroIdNameMap;
	}

	public Map<Integer, String> getHeroIdNameMap() {
		return heroIdNameMap;
	}
}

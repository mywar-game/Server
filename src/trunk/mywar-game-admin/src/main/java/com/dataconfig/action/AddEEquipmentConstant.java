package com.dataconfig.action;

import java.util.Map;

import com.dataconfig.bo.EEquipmentConstant;
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.HHeroConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddEEquipmentConstant extends ALDAdminActionSupport implements ModelDriven<EEquipmentConstant> {

	private static final long serialVersionUID = -2561698828694705805L;

	private EEquipmentConstant eequipmentConstant = new EEquipmentConstant();
	
	private Map<Integer, String> heroIdNameMap;
	
	private String isCommit = "F";

	public String execute() {
		HHeroConstantService heroConstantService = ServiceCacheFactory.getServiceCache().getService(HHeroConstantService.class);
		heroIdNameMap = heroConstantService.findHeroIdNameMap();
		if ("F".equals(this.isCommit)) {
			return INPUT;
		} else {
			EEquipmentConstantService eEquipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
			if (eEquipmentConstantService.findOneEEquipmentConstant(eequipmentConstant.getEquipmentId()) != null) {
				super.setErroDescrip(this.getText("addEEquipmentConstant.thisEquipmentIdExist", new String[]{eequipmentConstant.getEquipmentId() + ""}));
				return ERROR;
			}
			eEquipmentConstantService.addOneEEquipmentConstant(eequipmentConstant);
			return "ok";
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

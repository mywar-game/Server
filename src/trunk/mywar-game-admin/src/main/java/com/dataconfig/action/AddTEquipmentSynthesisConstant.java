package com.dataconfig.action;

import java.util.List;
import java.util.Map;

import com.dataconfig.bo.TEquipmentSynthesisConstant;
import com.dataconfig.constant.TTreasureTypeConstant;
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.TEquipmentSynthesisConstantService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddTEquipmentSynthesisConstant  extends ALDAdminActionSupport implements ModelDriven<TEquipmentSynthesisConstant> {
	
	/** **/
	private static final long serialVersionUID = 1L;

	/** **/
	private TEquipmentSynthesisConstant tequipmentSynthesisConstant = new TEquipmentSynthesisConstant();
	
	/** **/
	private String isCommit = "F";
	
	/** **/
	private Map<Integer, String> synthesisMaterialIdNameMap;
	
	/** **/
	private Map<Integer, String> equipmentIDNameMap;
	
	public String execute() {
		TEquipmentSynthesisConstantService tequipmentSynthesisConstantService = ServiceCacheFactory.getServiceCache().getService(TEquipmentSynthesisConstantService.class);
		if ("F".equals(isCommit)) {
			TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
			synthesisMaterialIdNameMap = treasureConstantService.findTypeTreasureIdNameMap(TTreasureTypeConstant.SYNTHESIS_MATERIAL);
			
			EEquipmentConstantService equipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
			equipmentIDNameMap = equipmentConstantService.findEquipmentIDNameMap();
			
			List<TEquipmentSynthesisConstant> list = tequipmentSynthesisConstantService.findTEquipmentSynthesisConstantList();
			for (TEquipmentSynthesisConstant equipmentSynthesisConstant : list) {
				equipmentIDNameMap.remove(equipmentSynthesisConstant.getEquipmentId());
			}
			
			return INPUT;
		} else {
			tequipmentSynthesisConstantService.addTEquipmentSynthesisConstant(tequipmentSynthesisConstant);
			return SUCCESS;
		}
	}

	@Override
	public TEquipmentSynthesisConstant getModel() {
		// TODO Auto-generated method stub
		return tequipmentSynthesisConstant;
	}

	public TEquipmentSynthesisConstant getTequipmentSynthesisConstant() {
		return tequipmentSynthesisConstant;
	}

	public void setTequipmentSynthesisConstant(
			TEquipmentSynthesisConstant tequipmentSynthesisConstant) {
		this.tequipmentSynthesisConstant = tequipmentSynthesisConstant;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public void setSynthesisMaterialIdNameMap(
			Map<Integer, String> synthesisMaterialIdNameMap) {
		this.synthesisMaterialIdNameMap = synthesisMaterialIdNameMap;
	}

	public Map<Integer, String> getSynthesisMaterialIdNameMap() {
		return synthesisMaterialIdNameMap;
	}

	public void setEquipmentIDNameMap(Map<Integer, String> equipmentIDNameMap) {
		this.equipmentIDNameMap = equipmentIDNameMap;
	}

	public Map<Integer, String> getEquipmentIDNameMap() {
		return equipmentIDNameMap;
	}

}
